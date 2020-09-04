package vehicles;

import java.awt.image.BufferedImage;

import graphics.CityPanel;
import vehicles.decorators.IVehicle;

public abstract class HasEngine extends Vehicle
{
    private int distance = 0;
    protected Engine engine;
    private double fuelAmount;

    public HasEngine(Engine engine)
    {
	super();
	fuelAmount = 40666;
	this.engine = engine;
    }

    /**
     * 
     * @param number
     * @param color
     * @param wheels
     * @param location
     * @param km
     * @param lights
     * @param fuelConsumption
     * @param pan
     * @param engine
     * @param fuelAmount
     * @param minAge
     */
    public HasEngine(int size, int number, int wheels, Location location, double km, boolean lights,
	    int fuelConsumption, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4,
	    Engine engine, double fuelAmount)
    {
	super(size, wheels, location, km, lights, fuelConsumption, img1, img2, img3, img4);

	this.engine = engine;
	this.fuelAmount = fuelAmount;
    }
    
    public HasEngine(Engine engine, Colors checkedColor)
    {
	this(engine);
	setColor(checkedColor);
    }

    /**
     * 
     * @return true/false
     */
    public boolean Refuel()
    {
	if (engine.getFuelVolume() == fuelAmount)
	{
	    System.out.println("Tank is already full!");
	    return false;
	}
	fuelAmount = engine.getFuelVolume();
	System.out.println("Refueled!");
	super.nextLocation();
	return true;
    }

    /**
     * @param point
     * @return true/false
     */
    @Override
    public boolean drive(Point point)
    {
	distance = Math.abs(point.getX() - getLocation().getPoint().getX())
		+ Math.abs(point.getY() - getLocation().getPoint().getY());
	double fuelNeeded = distance * engine.getFuelPerKM();
	if (fuelNeeded <= getFuelAmount())
	{
	    getLocation().setPoint(point);
	    setKm(getKm() + distance);
	    fuelNeeded = fuelAmount - fuelNeeded;
	    setFuelAmount(fuelNeeded);
	    if(fuelNeeded == 0)
	    {
		super.notifyFuelOut();
	    }
	    return true;
	}
	return false;
    }

    /**
     * 
     * @return engine
     */
    public Engine getEngine()
    {
	return engine;
    }

    /**
     * 
     * @param engine
     * @return true
     */
    public boolean setEngine(Engine engine)
    {
	this.engine = engine;
	return true;
    }

    /**
     * 
     * @return fuelAmount
     */
    public double getFuelAmount()
    {
	return fuelAmount;
    }

    /**
     * 
     * @param fuelAmount
     * @return true
     */
    public boolean setFuelAmount(double fuelAmount)
    {
	this.fuelAmount = fuelAmount;
	return true;
    }

    /**
     * 
     * @return minAge
     */

    @Override
    public String toString()
    {
	return super.toString() + engine + ", fuelAmount=" + getFuelAmount() + ", minAge=" + super.getminAge() + " ";
    }
}