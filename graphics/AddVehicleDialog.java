package graphics;

import vehicles.*;
import vehicles.decorators.IVehicle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @Class {@link AddVehicleDialog}
 *
 */
public class AddVehicleDialog extends JDialog implements ActionListener, Runnable
{
    JFrame frame = new JFrame("Error");

    private static int numOfVehicles = 0;
    private boolean gearInfo = false;

    private static AddVehicleDialog instance;

    private AddVehicleDialog()
    {

    }

    public static AddVehicleDialog getInstance()
    {
	if (instance == null)
	{
	    instance = new AddVehicleDialog();
	}
	return instance;
    }

    private JRadioButton[] buttonsColors = { new JRadioButton("Red"), new JRadioButton("Green"),
	    new JRadioButton("Silver"), new JRadioButton("White") };
    private static JFrame dialog;

    @Override
    public void actionPerformed(ActionEvent e)
    {
	dialog = new JFrame("Add a vehicle to the city");
	JPanel mainPanel = new JPanel();
	mainPanel.setLayout(new BorderLayout(0, 0));
	JButton buttonOk = new JButton("Ok");
	JButton buttonCancel = new JButton("Cancel");
	mainPanel.setLayout(new FlowLayout());
	dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JRadioButton[] buttonsVehicles = { new JRadioButton("Benzine Car"), new JRadioButton("Solar Car"),
		new JRadioButton("Bike"), new JRadioButton("Carriage") };

	JPanel radioPanel1 = new JPanel();
	JPanel radioPanel2 = new JPanel();
	ButtonGroup bgroup1 = new ButtonGroup();
	ButtonGroup bgroup2 = new ButtonGroup();
	for (int i = 0; i < 4; i++)
	{
	    bgroup1.add(buttonsVehicles[i]);
	    radioPanel1.add(buttonsVehicles[i]);
	    bgroup2.add(buttonsColors[i]);
	    radioPanel2.add(buttonsColors[i]);
	}
	radioPanel1.setBorder(
		BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Choose a vehicle type"));
	radioPanel1.setLayout(new GridLayout(2, 2));
	mainPanel.add(radioPanel1, BorderLayout.LINE_START);
	radioPanel2.setBorder(
		BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Choose a vehicle type"));
	radioPanel2.setLayout(new GridLayout(2, 2));
	mainPanel.add(radioPanel2, BorderLayout.LINE_END);
	mainPanel.add(buttonOk, BorderLayout.PAGE_END);

	buttonsVehicles[2].addActionListener(new ActionListener()
	{

	    public void actionPerformed(ActionEvent e)
	    {
		final int GEAR_MIN = 0;
		final int GEAR_MAX = 10;
		final int GEAR_INIT = 5; // initial gear number
		gearInfo = false;
		JSlider gear = new JSlider(JSlider.HORIZONTAL, GEAR_MIN, GEAR_MAX, GEAR_INIT);
		if (gearInfo == false)
		{

		    JLabel label = new JLabel("Choose bike gears");
		    gear.setMajorTickSpacing(2);
		    gear.setMinorTickSpacing(1);
		    gear.setPaintTicks(true);
		    gear.setPaintLabels(true);

		    mainPanel.add(label, BorderLayout.CENTER);
		    mainPanel.add(gear, BorderLayout.SOUTH);
		    dialog.pack();
		}
		gearInfo = true;
		buttonOk.addActionListener(new ActionListener()
		{

		    public void actionPerformed(ActionEvent e)
		    {
			if (buttonsVehicles[2].isSelected())
			{
			    Bike bike;

			    if (checkIfAnyColorChoosen())
			    {
				bike = VehicleFactory.getBike(CheckColor(buttonsColors), gear.getValue());

				CityPanel.addVehicleToCity(bike);

				CityPanel.getInstance().getVehicleList().add(bike);
			    }

			    CityPanel.getInstance().repaint();
			}

		    }
		});

	    }

	});

	buttonOk.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent e)
	    {
		if (!checkIfAnyColorChoosen())
		{
		    return;
		}

		if (buttonsVehicles[0].isSelected())
		{
		    Vehicle vehicle;

		    vehicle = VehicleFactory.getVehicle(VehicleType.BenzineCar, CheckColor(buttonsColors));

		    CityPanel.addVehicleToCity(vehicle);
		    CityPanel.getInstance().getVehicleList().add(vehicle);

		}
		if (buttonsVehicles[1].isSelected())
		{
		    Vehicle vehicle;

		    vehicle = (Vehicle) VehicleFactory.getVehicle(VehicleType.SolarCar, CheckColor(buttonsColors));
		    CityPanel.addVehicleToCity(vehicle);
		    CityPanel.getInstance().getVehicleList().add(vehicle);

		}

		if (buttonsVehicles[3].isSelected())
		{
		    Vehicle carriage;

		    carriage = (Vehicle) VehicleFactory.getVehicle(VehicleType.Carriage, CheckColor(buttonsColors));
		    CityPanel.addVehicleToCity(carriage);
		    CityPanel.getInstance().getVehicleList().add(carriage);

		}
		CityPanel.getInstance().repaint();
		dialog.setVisible(false);
	    }
	});
	mainPanel.add(buttonCancel, BorderLayout.PAGE_START);
	buttonCancel.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent e)
	    {
		dialog.setVisible(false);
	    }
	});
	dialog.add(mainPanel);
	dialog.setVisible(true);
	dialog.pack();

    }

    public boolean checkIfAnyColorChoosen()
    {
	for (int i = 0; i < 4; i++)
	{
	    if (buttonsColors[i].isSelected())
	    {
		return true;
	    }

	}
	JFrame frame = new JFrame();
	JOptionPane.showMessageDialog(frame, "no color choosed", "Error", JOptionPane.PLAIN_MESSAGE);
	return false;
    }

    public static void setnumOfVehicles(int num)
    {
	numOfVehicles = num;
    }

    public Colors CheckColor(JRadioButton[] buttonsColors)
    {
	for (int i = 0; i < 4; i++)
	    if (buttonsColors[i].isSelected())
	    {
		String x = buttonsColors[i].getActionCommand();
		switch (x)
		{
		    case "Red":
		    {
			return Colors.Red;

		    }
		    case "Green":
		    {
			return Colors.Green;
		    }
		    case "White":
		    {
			return Colors.White;
		    }
		    case "Silver":
		    {
			return Colors.Silver;
		    }
		}
	    }
	return null;
    }

    @Override
    public void run()
    {

    }
}