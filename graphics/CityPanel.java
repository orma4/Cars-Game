package graphics;

import vehicles.*;
import graphics.AddVehicleDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableColumnModel;
import vehicles.Vehicle;
import vehicles.decorators.IVehicle;

/**
 * 
 * @Class {@link CityPanel}
 *
 */
public class CityPanel extends JPanel implements Observer
{
    private BufferedImage img = null;
    private static Thread[] threads = new Thread[5];
    public static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    public static int vehicleCounter = 0;
    public static ArrayList<Vehicle> vehicleQueue = new ArrayList<Vehicle>();
    private static ArrayList<Observer> isExitPressedObservers = new ArrayList<Observer>();

    public static void addObserver(Observer observer)
    {
	isExitPressedObservers.add(observer);
    }

    public static void removeObserver(Observer observer)
    {
	isExitPressedObservers.remove(observer);
    }

    public void notifyExitPreesed()
    {
	System.out.println("here");
	for (Observer observer : isExitPressedObservers)
	{
	    System.out.println("closed");
	    observer.update();
	}
    }

    private CityPanel()
    {
	BackGroundImage();
	createComponents();
    }

    private static CityPanel cityPanel;

    public static CityPanel getInstance()
    {
	if (cityPanel == null)
	    cityPanel = new CityPanel();
	return cityPanel;
    }

    public static void addVehicleToCity(Vehicle vehicle)
    {
	if (vehicleCounter == 10)
	{
	    JFrame frame = new JFrame();
	    JOptionPane.showMessageDialog(frame, "can't add more than 5 to vehicle queue", "Error",
		    JOptionPane.PLAIN_MESSAGE);
	    return;
	}
	if (vehicleCounter - vehicleQueue.size() > 5)
	{
	    vehicleQueue.add(vehicle);
	    return;
	}
	addObserver((Vehicle)vehicle);
	vehicle.addObserver(CityPanel.getInstance());
	vehicle.addDataObserver(Info.getInstance());
	vehicles.add(vehicle);
	int index = vehicles.indexOf(vehicle);

	if (vehicles.get(index) != null)
	{

	    threads[index] = new Thread(vehicle);
	    threads[index].start();
	    setVehicleNum((Vehicle) vehicles.get(index));
	}
    }

    /**
     * 
     * @param vehicleNum
     * @return boolean
     */
    public boolean clearVehicle(int vehicleNum)
    {
	for (int i = 0; i < vehicles.size(); i++)
	{
	    Vehicle v = vehicles.get(i);

	    if (v != null)
	    {
		if (vehicleNum == ((Vehicle) v).getNumber())
		{

		    ((Vehicle)v).running = false;
		    removeObserver(getInstance().getVehicleList().remove(i));
		    v.removeObserver(CityPanel.getInstance());
		    v.removeDataObserver(Info.getInstance());
		    vehicles.remove(i);
		    
		    if (vehicles.size() == 4)
		    {
			if (vehicleQueue.size() == 1)
			{
			    addVehicleToCity((Vehicle) vehicleQueue.remove(0));
			    return true;

			}
		    }
		    if (vehicles.size() <= 3 && vehicleQueue.size() != 0)
		    {
			if (vehicleQueue.size() == 1)
			{
			    addVehicleToCity((Vehicle) vehicleQueue.remove(0));
			    return true;
			}
			else
			{
			    if (vehicleQueue.size() != 0)
			    {
				addVehicleToCity((Vehicle) vehicleQueue.remove(0));
				try
				{
				    Thread.sleep(10000);
				}
				catch (InterruptedException e)
				{
				    //
				    e.printStackTrace();
				}
				if (vehicleQueue.size() != 0)
				{
				    addVehicleToCity((Vehicle) vehicleQueue.remove(0));
				}
			    }
			}
		    }
		}
	    }
	}
	return true;

    }

    public ArrayList<Vehicle> getVehicles()
    {
	return vehicles;
    }

    private CityPanel panel = this;
    private ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
    private static int vehicleNum = 0;

    public static int getVehicleNum()
    {
	return vehicleNum;
    }

    public static void setVehicleNum(Vehicle vehicle)
    {
	vehicle.SetNumber(1000 + CityPanel.vehicleNum);
	CityPanel.vehicleNum += 1;

    }

    public ArrayList<Vehicle> getVehicleList()
    {
	return vehicleList;
    }

    public void setVlist(ArrayList<Vehicle> vlist)
    {
	this.vehicleList = vlist;
    }

    public BufferedImage BackGroundImage()
    {
	try
	{
	    img = ImageIO.read(new File(IDrawable.PICTURE_PATH + "cityBackground.png"));
	}
	catch (IOException e)
	{
	    System.out.println("Cannot load image");
	}
	return img;
    }

    protected void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	for (int i = 0; i < vehicles.size(); i++)
	{
	    Vehicle v = (Vehicle) vehicles.get(i);
	    if (v != null)
	    { // if the vehicle object exists
		v.drawObject(g);
	    }
	}

    }

    //
    JButton buttons[] = { new JButton("Add Vehicle"), new JButton("Clear"), new JButton("Fuel/Food"),
	    new JButton("Lights"), new JButton("Info"), new JButton("Exit") };

    public Component createComponents()
    {
	JPanel pane = new JPanel(new BorderLayout());
	BorderLayout myBorderLayout = new BorderLayout(10, 10);
	pane.setLayout(myBorderLayout);
	pane.setLayout(new GridLayout(1, 6));
	JPanel mainPanel = new JPanel(new BorderLayout(100, 100));

	for (int i = 0; i < 6; i++)
	    pane.add(buttons[i]);
	buttons[0].addActionListener(AddVehicleDialog.getInstance());
	buttons[1].addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent e)
	    {
		if (vehicles.size() == 0)
		{
		    JFrame frame = new JFrame();
		    // JOptionPane.showMessageDialog(frame,
		    // "There isn't any vehicle yet");
		    JOptionPane.showMessageDialog(frame, "There isn't any vehicle yet", "Error",
			    JOptionPane.PLAIN_MESSAGE);

		    return;
		}
		String[] idVeichle = new String[vehicles.size()];
		for (int i = 0; i < idVeichle.length; i++)
		{
		    idVeichle[i] = Integer.toString(((Vehicle) vehicles.get(i)).getNumber());

		}
		String input = (String) JOptionPane.showInputDialog(null, "Choose now...",
			"The Choice of a veichle number to clear", JOptionPane.QUESTION_MESSAGE, null, idVeichle, // Array
														  // of
														  // choices
			null); // Initial choice

		if (input != null)
		{
		    CityPanel.vehicleCounter--;
		    clearVehicle(Integer.parseInt(input));
		}
	    }
	});

	buttons[2].addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		JFrame f = new JFrame();
		String[] idVeichle = new String[vehicles.size()];
		for (int i = 0; i < idVeichle.length; i++)
		{
		    idVeichle[i] = Integer.toString(((Vehicle) vehicles.get(i)).getNumber());
		}
		if (vehicles.size() == 0)
		{
		    JFrame frame = new JFrame();
		    JOptionPane.showMessageDialog(frame, "There isn't any vehicle yet", "Error",
			    JOptionPane.PLAIN_MESSAGE);

		    return;
		}
		String input = (String) JOptionPane.showInputDialog(f, "Choose now...",
			"The Choice of a veichle number", // Array of choices
			JOptionPane.QUESTION_MESSAGE, null, idVeichle, idVeichle[0]); // Initial choice

		int n = -1;
		if (input != null)
		{
		    Object[] options = { "Benzine", "Solar", "Food" };
		    n = JOptionPane.showOptionDialog(f, "Please choose food", "Fuel for cars/Food for animals",
			    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
		}
		else
		{
		    return;
		}
		for (IVehicle vehicle : vehicles)
		{
		    if (((Vehicle) vehicle).getNumber() == Integer.parseInt(input))
		    {
			int index = vehicles.indexOf(vehicle);

			synchronized (threads[index])
			{
			    if (vehicles.get(index) instanceof Carriage)
			    {
				if ((n == 0) || (n == 1))
				{
				    JOptionPane.showMessageDialog(null, "Incorrect food, please select Food!", "Error",
					    JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				    ((Carriage) vehicles.get(index)).getP().eat();
				    ((Vehicle) vehicles.get(index)).setFuelConsumption(1000);
				}
			    }
			    if (vehicles.get(index) instanceof Bike)
			    {
				JOptionPane.showMessageDialog(null, "Cannot refuel bike!", "Error",
					JOptionPane.ERROR_MESSAGE);
			    }
			    if (vehicles.get(index) instanceof Car)
			    {
				if (((HasEngine) vehicles.get(index)).getEngine().getFuelPerKM() == 2)
				    if ((n == 1) || (n == 2))
				    {
					JOptionPane.showMessageDialog(null, "Incorrect fuel, please select Benzine!",
						"Error", JOptionPane.ERROR_MESSAGE);
				    }
				    else
				    {
					((HasEngine) vehicles.get(index)).Refuel();
					((Vehicle) vehicles.get(index)).setFuelConsumption(40);
				    }

				if (((HasEngine) vehicles.get(index)).getEngine().getFuelPerKM() == 1)
				    if ((n == 0) || (n == 2))
				    {
					JOptionPane.showMessageDialog(null, "Incorrect fuel, please select Solar!",
						"Error", JOptionPane.ERROR_MESSAGE);
				    }
				    else
				    {
					((HasEngine) vehicles.get(index)).Refuel();
					((Vehicle) vehicles.get(index)).setFuelConsumption(40);
				    }
			    }

			    threads[index].notify();
			}
		    }
		}
	    }
	});
	buttons[3].addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent arg0)
	    {
		if (vehicleNum == 0)
		{
		    JFrame frame = new JFrame("Error");// class message
		    JOptionPane.showMessageDialog(frame, "There is no any vehicle yet.");
		    return;
		}
		for (IVehicle v : vehicles)
		{
		    ((Vehicle) v).setLights(!((Vehicle) v).isLights());
		}
	    }
	});
	// info clicked
	buttons[4].addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		Info.getInstance().showOrHide();
	    }
	});

	// exit pressed
	buttons[5].addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		notifyExitPreesed();
		System.exit(0);
	    }
	});
	mainPanel.add(pane);
	return mainPanel;
    }

    @Override
    public void update()
    {
	buttons[2].setBackground(Color.RED);
    }
}