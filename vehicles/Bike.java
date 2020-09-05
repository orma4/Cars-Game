package vehicles;

import graphics.CityPanel;
import vehicles.decorators.IVehicle;

import java.awt.image.BufferedImage;

public class Bike extends Vehicle
{
    private static final int numOfPassengers = 1;
    private static final int speed = 2;
    private int numOfGear;

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
     * @param numOfGear
     */
    public Bike(int size, int number, Colors color, int wheels, Location location, double km, boolean lights,
	    int fuelConsumption, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4,
	    int numOfGear, IVehicle deocratedVehicle)
    {
	super(size, /* color, */ wheels, location, km, lights, fuelConsumption, img1, img2, img3, img4,
		deocratedVehicle);
	this.numOfGear = numOfGear;
	super.setDurability(1);
    }

    public Bike(/* Colors color, */ int numOfGear, IVehicle deocratedVehicle)
    {
	super(deocratedVehicle);
	this.numOfGear = numOfGear;
	super.setFuelConsumption(0);
	super.setWheels(2);
	super.setDurability(1);
	loadImages("Bike");
    }

    public Bike(int gearValue)
    {

	this.numOfGear = gearValue;
	super.setFuelConsumption(0);
	super.setWheels(2);
	super.setDurability(1);
	
    }

    public Bike(int gearValue, Colors checkedColor)
    {
	this(gearValue);
	this.setColor(checkedColor);
	loadImages("Bike");
    }

    /**
     * 
     * @return numOfGear
     */
    public static int getNumOfPassengers()
    {
	return numOfPassengers;
    }

    public int getNumOfGear()
    {
	return numOfGear;
    }

    /**
     * 
     * @param numOfGear
     * @return true
     */
    public boolean setNumOfGear(int numOfGear)
    {
	this.numOfGear = numOfGear;
	return true;
    }

    /**
     * 
     * @return speed
     */
    public int getSpeed()
    {
	return speed;
    }

    @Override
    public String toString()
    {
	return "Bike" + super.toString() + "numOfGear=" + numOfGear + "]";
    }

    @Override
    public String getVehicleName()
    {
	return "Bike";
    }

    @Override
    public int getFuelConsumption()
    {
	return 0;
    }

}