package vehicles;

public class SolarEngine extends Engine 
{
	private final static int fuelPerKM = 1;
	/**
	 * 
	 * @param fuelVolume
	 * @param fuelPerKM
	 */
	public SolarEngine(int fuelVolume) 
	{
		super(fuelPerKM,fuelVolume);
	}
	
	public SolarEngine()
	{
	}
}
