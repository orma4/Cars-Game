package vehicles;

import graphics.CityPanel;
import graphics.IDrawable;
import graphics.IMoveable;
import vehicles.decorators.IVehicle;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
/**
 * 
 * @Class {@link Vehicle}
 *
 */
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class Vehicle implements IMoveable, IDrawable, Cloneable, Runnable, IVehicle, Observer
{
    private static final int minAge = 18;
    private int size;
    private int number = 1000;
    private Colors color;
    private int wheels;
    protected Location location;
    protected double km;
    private boolean lights;
    private int fuelConsumption;
    private boolean junctionFlag = false;
    private boolean canMove = true;
    private BufferedImage img1 = null, img2, img3, img4;
    private int Durability;
    private String crashedWith = "None";
    private boolean border = false;

    private ArrayList<Observer> isFuelOutObservers = new ArrayList<Observer>();
    private ArrayList<Observer> isDataChangedObserves = new ArrayList<Observer>();

    public void addObserver(Observer observer)
    {
	isFuelOutObservers.add(observer);
    }

    public void removeObserver(Observer observer)
    {
	isFuelOutObservers.remove(observer);
    }

    public void notifyFuelOut()
    {
	
	for (Observer observer : isFuelOutObservers)
	{
	    
	    observer.update();
	}
    }
    
    public void addDataObserver(Observer observer)
    {
	isDataChangedObserves.add(observer);
    }

    public void removeDataObserver(Observer observer)
    {
	isDataChangedObserves.remove(observer);
    }

    public void notifyDataChanged()
    {
	
	for (Observer observer : isDataChangedObserves)
	{
	    
			observer.update();
	}
    }

    /**
     * 
     * @param color
     * @param panel
     * @param decoratedVehicle
     */
    public Vehicle(IVehicle decoratedVehicle)
    {
	location = new Location();
	lights = false;
	km = 0;
	fuelConsumption = 0;
	// setColor(color);
	
	setSize(65);

	CityPanel.vehicleCounter++;
    }

    /**
     * @param size
     * @param number
     * @param color
     * @param wheels
     * @param location
     * @param km
     * @param lights
     * @param fuelConsumption
     * @param pan
     */
    public Vehicle(int size, int wheels, Location location, double km, boolean lights, int fuelConsumption,
	    BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, IVehicle decoratedVehicle)
    {
	this(decoratedVehicle);
	this.size = size;
	this.wheels = wheels;
	this.location = location;
	this.km = km;
	this.lights = lights;
	this.setFuelConsumption(fuelConsumption);

	this.img1 = img1;
	this.img2 = img2;
	this.img3 = img3;
	this.img4 = img4;

    }

    public Vehicle(int size, int wheels, Location location, double km, boolean lights, int fuelConsumption,
	    BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4)
    {
	this();
	this.size = size;
	this.wheels = wheels;
	this.location = location;
	this.km = km;
	this.lights = lights;
	this.setFuelConsumption(fuelConsumption);

	this.img1 = img1;
	this.img2 = img2;
	this.img3 = img3;
	this.img4 = img4;

    }

    public Vehicle()
    {
	location = new Location();
	lights = false;
	km = 0;
	fuelConsumption = 0;
	// setColor(color);
	setSize(65);

	CityPanel.vehicleCounter++;
    }

    public void update()
    {
	running = false;
    }

    public boolean isBorder()
    {
	return border;
    }

    public void setBorder(boolean border)
    {
	this.border = border;
    }

    /**
     * 
     * @return crashedWith
     */
    public String getCrashedWith()
    {
	return this.crashedWith;
    }

    /**
     * 
     * @param crashedWith
     */
    public void setCrashedWith(String crashedWith)
    {
	this.crashedWith = crashedWith;
    }

    public boolean running = true;

    /**
     * Thread run function
     */
    @Override
    public void run()
    {
	Vehicle myVehicle = null;
	Vehicle otherVehicle;
	while (running)
	{

	    try
	    {
		this.move(this.nextLocation());
	    }
	    catch (InterruptedException e1)
	    {
		e1.printStackTrace();
	    }

	    for (int i = 0; i < CityPanel.vehicles.size(); i++)
	    {
		IVehicle v = CityPanel.vehicles.get(i);
		if (v != null)
		{
		    if (!v.equals(this))
		    {
			myVehicle = this;
			otherVehicle = (Vehicle) v;
			if (myVehicle.getBounds().intersects(otherVehicle.getBounds()))
			{
			    System.out.println("Crash!");
			    if (myVehicle.getDurability() == otherVehicle.getDurability())
			    {
				setCrashedWith(Integer.toString(otherVehicle.getNumber()));
				CityPanel.vehicleCounter--;
				CityPanel.getInstance().clearVehicle(myVehicle.getNumber());
				otherVehicle.setCrashedWith(Integer.toString(myVehicle.getNumber()));
				CityPanel.vehicleCounter--;
				CityPanel.getInstance().clearVehicle(otherVehicle.getNumber());
			    }
			    else
			    {
				if (myVehicle.getDurability() > otherVehicle.getDurability())
				{
				    otherVehicle.setCrashedWith(Integer.toString(myVehicle.getNumber()));
				    CityPanel.vehicleCounter--;
				    CityPanel.getInstance().clearVehicle(otherVehicle.getNumber());
				}
				else
				{
				    myVehicle.setCrashedWith(Integer.toString(otherVehicle.getNumber()));
				    CityPanel.vehicleCounter--;
				    CityPanel.getInstance().clearVehicle(myVehicle.getNumber());
				}
			    }
			}
		    }
		}
	    }
	    notifyDataChanged();
	    try
	    {
		Thread.sleep(100 / this.getSpeed());
	    }
	    catch (InterruptedException e)
	    {
		e.printStackTrace();
	    }

	}
	if (myVehicle != null)
	{
	    CityPanel.getInstance().clearVehicle(myVehicle.getNumber());
	}
    }

    public Rectangle getBounds()
    {
	if (getLocation().getOrientation() == Orientation.North || getLocation().getOrientation() == Orientation.South)
	{
	    return new Rectangle(getLocation().getPoint().getX(), getLocation().getPoint().getY(), size, size * 2);
	}
	return new Rectangle(getLocation().getPoint().getX(), getLocation().getPoint().getY(), size * 2, size);
    }

    public boolean equals(Vehicle other)
    {
	if (this.number == other.number)
	    return true;
	return false;
    }

    public int getDurability()
    {
	return Durability;
    }

    public void setDurability(int durability)
    {
	Durability = durability;

    }

    public boolean SetNumber(int number)
    {
	this.number = number;
	return true;
    }

    public int getSize()
    {
	return size;
    }

    public void setSize(int size)
    {
	this.size = size;
    }

    public BufferedImage getImg1()
    {
	return img1;
    }

    public void setImg1(BufferedImage img1)
    {
	this.img1 = img1;
    }

    public BufferedImage getImg2()
    {
	return img2;
    }

    public void setImg2(BufferedImage img2)
    {
	this.img2 = img2;
    }

    public BufferedImage getImg3()
    {
	return img3;
    }

    public void setImg3(BufferedImage img3)
    {
	this.img3 = img3;
    }

    public BufferedImage getImg4()
    {
	return img4;
    }

    public void setImg4(BufferedImage img4)
    {
	this.img4 = img4;
    }

    /**
     * 
     * @return number
     */
    public int getNumber()
    {
	return this.number;
    }

    /**
     * 
     * @return color
     */
    public Colors getColor()
    {
	return color;
    }

    /**
     * 
     * @param color
     * @return true
     */
    public boolean setColor(Colors color)
    {
	this.color = color;
	return true;

    }

    /**
     * 
     * @return wheels
     */
    public int getWheels()
    {
	return wheels;
    }

    /**
     * 
     * @param wheels
     * @return true
     */
    public boolean setWheels(int wheels)
    {
	this.wheels = wheels;
	return true;
    }

    /**
     * 
     * @return location
     */
    public Location getLocation()
    {
	return location;
    }

    /**
     * 
     * @param location
     * @return true
     */
    public boolean setLocation(Location location)
    {
	this.location = location;
	return true;
    }

    /**
     * 
     * @return KM
     */
    public double getKm()
    {
	return km;
    }

    /**
     * 
     * @param km
     * @return true
     */
    public boolean setKm(double km)
    {
	this.km = km;
	return true;
    }

    /**
     * 
     * @return lights
     */
    public boolean isLights()
    {
	return lights;
    }

    /**
     * 
     * @param lights
     * @return true
     */
    public boolean setLights(boolean lights)
    {
	this.lights = lights;
	return true;
    }

    /**
     * 
     * @param point
     * @return true Will be override in HasEngine and Carriage
     */
    public boolean drive(Point point)
    {
	km += Math.abs(point.getX() - location.getPoint().getX()) + Math.abs(point.getY() - location.getPoint().getY());
	location.setPoint(point);

	return true;
    }

    @Override
    public String toString()
    {
	return " [number=" + getNumber() + ", durabilty=" + getDurability() + ", color=" + getColor() + ", wheels="
		+ wheels + ", location=" + location + ", km=" + km + ", lights=" + lights + ", FuelConsumption= "
		+ fuelConsumption + " ";
    }

    public Object clone()
    {
	return null;
    }

    @Override
    public int getFuelConsumption()
    {
	return fuelConsumption;
    }

    public boolean setFuelConsumption(int fuelConsumption)
    {
	this.fuelConsumption += fuelConsumption;
	return true;
    }

    public int getminAge()
    {

	return minAge;
    }

    @Override
    public void loadImages(String nm)
    {
	String im1 = null, im2 = null, im3 = null, im4 = null;
	
	
	if (this.getColor() == Colors.White)
	{
	    im1 = "white" + nm + "North.png";
	    im2 = "white" + nm + "South.png";
	    im3 = "white" + nm + "East.png";
	    im4 = "white" + nm + "West.png";

	}

	if (this.getColor() == Colors.Red)
	{
	    im1 = "red" + nm + "North.png";
	    im2 = "red" + nm + "South.png";
	    im3 = "red" + nm + "East.png";
	    im4 = "red" + nm + "West.png";

	}
	if (this.getColor() == Colors.Silver)
	{
	    im1 = "silver" + nm + "North.png";
	    im2 = "silver" + nm + "South.png";
	    im3 = "silver" + nm + "East.png";
	    im4 = "silver" + nm + "West.png";
	}

	if (this.getColor() == Colors.Green)
	{
	    im1 = "green" + nm + "North.png";
	    im2 = "green" + nm + "South.png";
	    im3 = "green" + nm + "East.png";
	    im4 = "green" + nm + "West.png";
	}
	try
	{

	    img1 = ImageIO.read(new File(IDrawable.PICTURE_PATH + im1));
	    img2 = ImageIO.read(new File(IDrawable.PICTURE_PATH + im2));
	    img3 = ImageIO.read(new File(IDrawable.PICTURE_PATH + im3));
	    img4 = ImageIO.read(new File(IDrawable.PICTURE_PATH + im4));

	}
	catch (IOException e)
	{
	    System.out.println("Cannot load image");
	}
    }

    public void drawObject(Graphics g)
    {
	if (location.getOrientation() == Orientation.North) // drives to north
	    // side

	    g.drawImage(img1, location.getPoint().getX(), location.getPoint().getY(), size, size * 2,
		    CityPanel.getInstance());

	else
	    if (location.getOrientation() == Orientation.South)// drives to the
		// south side

		g.drawImage(img2, location.getPoint().getX(), location.getPoint().getY(), size, size * 2,
			CityPanel.getInstance());

	    else
		if (location.getOrientation() == Orientation.East)
		{ // drives to
		  // the east
		  // side

		    g.drawImage(img3, location.getPoint().getX(), location.getPoint().getY(), size * 2, size,
			    CityPanel.getInstance());
		}

		else
		    if (location.getOrientation() == Orientation.West) // drives to the
			// west side

			g.drawImage(img4, location.getPoint().getX(), location.getPoint().getY(), size * 2, size,
				CityPanel.getInstance());
    }

    public Point nextLocation()
    {
	Point nextPoint = null;
	int x, y;

	x = this.getLocation().getPoint().getX();
	y = this.getLocation().getPoint().getY();
	switch (this.getLocation().getOrientation())
	{
	    case West:
	    {
		if (x > 0 && y == 0)
		    nextPoint = new Point(x - getSpeed(), y);

		if (x > 0 && y == 220)
		    nextPoint = new Point(x - getSpeed(), y);

		if (x > 0 && y == 448)
		    nextPoint = new Point(x - getSpeed(), y);

		if (x == 0 && y == 0)
		{
		    getLocation().setOrientation(Orientation.South);
		    nextPoint = new Point(x, y);
		}
		if (x == 0 && y == 220)
		{
		    junctionFlag = true;
		    nextPoint = new Point(x, y);
		    getLocation().setOrientation(randomOrientation(Orientation.South, Orientation.North));
		}
		if (x == 0 && y == 448)
		{
		    getLocation().setOrientation(Orientation.North);
		    nextPoint = new Point(x, y);
		}
		break;
	    }
	    case East:
	    {
		if (x < 712 && y == 0)
		    nextPoint = new Point(x + getSpeed(), y);

		if (x < 712 && y == 220)
		    nextPoint = new Point(x + getSpeed(), y);

		if (x < 712 && y == 448)
		    nextPoint = new Point(x + getSpeed(), y);

		if (x == 712 && y == 0)
		{
		    nextPoint = new Point(x, y);
		    getLocation().setOrientation(Orientation.South);
		}

		if (x == 712 && y == 220)
		{
		    nextPoint = new Point(x, y);
		    junctionFlag = true;
		    getLocation().setOrientation(randomOrientation(Orientation.South, Orientation.North));
		}
		if (x == 712 && y == 448)
		{
		    nextPoint = new Point(x, y);
		    getLocation().setOrientation(Orientation.North);
		}
		break;
	    }

	    case South:
	    {
		if (y < 448 && x == 0)
		    nextPoint = new Point(x, y + getSpeed());

		if (y < 448 && x == 712)
		    nextPoint = new Point(x, y + getSpeed());

		if (x == 0 && y == 448)
		{
		    getLocation().setOrientation(Orientation.East);
		    nextPoint = new Point(x, y);
		}

		if ((x == 0 && y == 220) && (junctionFlag == false))
		{
		    nextPoint = new Point(x, y);
		    getLocation().setOrientation(randomOrientation(Orientation.South, Orientation.East));
		    if (getLocation().getOrientation() == Orientation.South)
			nextPoint = new Point(x, y + getSpeed());
		}

		if ((x == 0 && y == 220) && (junctionFlag == true))
		{
		    nextPoint = new Point(x, y + getSpeed());
		    junctionFlag = false;

		}

		if (x == 712 && y == 448)
		{
		    getLocation().setOrientation(Orientation.West);
		    nextPoint = new Point(x, y);
		}

		if ((x == 712 && y == 220) && (junctionFlag == false))
		{
		    nextPoint = new Point(x, y);
		    getLocation().setOrientation(randomOrientation(Orientation.South, Orientation.West));
		    if (getLocation().getOrientation() == Orientation.South)
			nextPoint = new Point(x, y + getSpeed());
		}

		if ((x == 712 && y == 220) && (junctionFlag == true))
		{
		    nextPoint = new Point(x, y + getSpeed());
		    junctionFlag = false;
		}
		break;
	    }

	    case North:
	    {
		if (y > 0 && x == 0)
		    nextPoint = new Point(x, y - getSpeed());

		if (y > 0 && x == 712)
		    nextPoint = new Point(x, y - getSpeed());

		if (x == 0 && y == 0)
		{
		    nextPoint = new Point(x, y);
		    getLocation().setOrientation(Orientation.East);
		}

		if ((x == 0 && y == 220) && (junctionFlag == false))
		{
		    nextPoint = new Point(x, y);
		    getLocation().setOrientation(randomOrientation(Orientation.North, Orientation.East));
		    if (getLocation().getOrientation() == Orientation.North)
			nextPoint = new Point(x, y - getSpeed());
		}

		if ((x == 0 && y == 220) && (junctionFlag == true))
		{
		    nextPoint = new Point(x, y - getSpeed());
		    junctionFlag = false;
		}

		if (x == 712 && y == 0)
		{
		    nextPoint = new Point(x, y);
		    getLocation().setOrientation(Orientation.West);
		}

		if ((x == 712 && y == 220) && (junctionFlag == false))
		{
		    nextPoint = new Point(x, y);
		    getLocation().setOrientation(randomOrientation(Orientation.North, Orientation.West));
		    if (getLocation().getOrientation() == Orientation.North)
			nextPoint = new Point(x, y - getSpeed());
		}

		if ((x == 712 && y == 220) && (junctionFlag == true))
		{
		    nextPoint = new Point(x, y - getSpeed());
		    junctionFlag = false;
		}
		break;
	    }
	}
	return nextPoint;
    }

    public Orientation randomOrientation(Orientation orien1, Orientation orien2)
    {
	int random = (int) (Math.random() * 2 + 1);
	if (random == 1)
	{
	    return orien1;
	}
	return orien2;
    }

    public void setCanMove(boolean bool)
    {
	this.canMove = bool;
    }

    public void move(Point point) throws InterruptedException
    {

	if (this instanceof Bike)
	    setCanMove(true);
	else
	    if (((HasEngine) this).getFuelAmount() == 0)
	    {
		synchronized (Thread.currentThread())
		{
		    setCanMove(false);
		    System.out.println("Not enough fuel, the vehicle didn't move!");
		    Thread.currentThread().wait();
		    setCanMove(true);
		}
	    }
	    else
		setCanMove(true);
	if (canMove)
	{
	    try
	    {
		Thread.sleep(100);
	    }
	    catch (InterruptedException e)
	    {
		e.printStackTrace();
	    }
	    this.drive(point);
	}
	CityPanel.getInstance().repaint();
    }

    public void draw()
    {
	
    }

    @Override
    public String getVehicleName()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int getSpeed()
    {
	// TODO Auto-generated method stub
	return 0;
    }

}