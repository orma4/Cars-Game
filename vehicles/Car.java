package vehicles;

import graphics.CityPanel;
import java.awt.image.BufferedImage;

public class Car extends HasEngine
{
    private static final int numOfWheelsInCar = 4;
    private static final int numOfPassengers = 5;
    private static final int fuelVolume = 40666;
    private static final int speed = 4;

    public Car(/* Colors color, */ Engine engine)
    {
	super(/* color, */ engine);
	setWheels(numOfWheelsInCar);
	getEngine().setFuelVolume(fuelVolume);
	
	super.setDurability(3);
    }

    /**
     * 
     * @param number
     * @param color
     * @param location
     * @param km
     * @param lights
     * @param engine
     * @param fuelAmount
     * @param minAge
     * @param numOfPassengers
     */
    public Car(int size, int number, Colors color, Location location, double km, boolean lights, int fuelConsumption,
	    CityPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4,
	    Engine engine, double fuelAmount, int numOfPassengers)
    {
	super(size, number, Car.numOfWheelsInCar, location, km, lights, fuelConsumption, img1, img2, img3,
		img4, engine, fuelAmount);
	getEngine().setFuelVolume(fuelVolume);
	super.setDurability(3);
    }

    

    public Car(Engine engine, Colors checkedColor)
    {
	this(engine);
	System.out.println(checkedColor);
	this.setColor(checkedColor);
	loadImages("Car");
    }

    /**
     * 
     * @return numOfPassengers
     */
    public int getNumOfPassengers()
    {
	return numOfPassengers;
    }

    /**
     * 
     * @return fuelVolume
     */
    public static int getFuelVolume()
    {
	return fuelVolume;
    }

    @Override
    public String toString()
    {
	return "Car" + super.toString() + "numOfPassengers=" + numOfPassengers + ", Speed= " + speed;
    }

    @Override
    public String getVehicleName()
    {
	if (this.engine instanceof SolarEngine)
	    return "Solar Car";
	return "Benzine Car";
    }

    @Override
    public int getSpeed()
    {
	return speed;
    }

}