package vehicles;

import graphics.CityPanel;
import vehicles.PackAnimal;

import java.awt.image.BufferedImage;

public class Carriage extends Vehicle
{
    private final int numOfPassengers = 2;
    private static final int speed = 1;
    private PackAnimal p;
    private boolean canMove;
    private int distance = 0;
    private final int wheelsOfCarriage = 4;

    /**
     * 
     * @param number
     * @param color
     * @param location
     * @param km
     * @param lights
     * 
     */
    public Carriage(int size, int number, Location location, double km, boolean lights, int fuelconsumption,
	    CityPanel pan, PackAnimal p, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4)
    {
	super(size, 4, location, km, lights, fuelconsumption, img1, img2, img3, img4);

	this.p = p;
	super.setDurability(2);
    }

    public Carriage(/* Colors color, */ )
    {
	super(/* color, */ );
	super.setWheels(4);
	p = new PackAnimal();
	
	super.setDurability(2);
    }

    public Carriage(Colors checkedColor)
    {
	this();
	this.setColor(checkedColor);
	loadImages("Carriage");
    }

    /**
     * 
     * @return p
     */

    public PackAnimal getP()
    {
	return p;
    }

    public void setP(PackAnimal p)
    {
	this.p = p;
    }

    /**
     * 
     * @return wheelsOfCarriage
     */
    public int getNumofpassengers()
    {
	return numOfPassengers;
    }

    public int getWheelsOfCarriage()
    {
	return wheelsOfCarriage;
    }

    @Override
    public String toString()
    {
	return "Carriage" + super.toString() + "typeOfAnimal=" + getP().getAnimalName() + "]";
    }

    @Override
    public String getVehicleName()
    {
	return "Carriage";
    }

    @Override
    public int getSpeed()
    {
	return speed;
    }

    public Colors getColor()
    {
	return super.getColor();
    }

    public Object clone()
    {
	return null;
    }

    @Override
    public boolean drive(Point point)
    {
	distance = Math.abs(point.getX() - getLocation().getPoint().getX())
		+ Math.abs(point.getY() - getLocation().getPoint().getY());
	setKm(getKm() + distance);
	location.setPoint(point);
	p.setEnergyPerKM(distance);
	if(p.getEnergy() == 0)
	{
	    super.notifyFuelOut();
	}
	return true;
    }

    public void move(Point point)
    {
	if (p.getEnergy() > 0)
	    canMove = true;
	else
	{
	    canMove = false;

	}
	if (canMove)
	{
	    try
	    {
		Thread.sleep(5);
	    }
	    catch (InterruptedException e)
	    {
		e.printStackTrace();
	    }
	    drive(point);
	}
	CityPanel.getInstance().repaint();
    }

}