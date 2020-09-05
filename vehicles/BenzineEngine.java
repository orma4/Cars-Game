package vehicles;

public class BenzineEngine extends Engine 
{
	private final static int fuelPerKM = 2;
	/**
	 * @param fuelPerKM
	 * @param fuelVolume
	 */
	public BenzineEngine(int fuelVolume) 
	{
		super(fuelPerKM,fuelVolume);
	}
}
