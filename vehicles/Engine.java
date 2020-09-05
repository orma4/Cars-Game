package vehicles;

public abstract class Engine {
	private int fuelPerKM;
	private int fuelVolume;

	/**
	 * 
	 * @param fuelPerKM
	 * @param fuelVolume
	 */
	public Engine(int fuelPerKM, int fuelVolume) {
		super();
		this.fuelPerKM = fuelPerKM;
		this.fuelVolume = fuelVolume;
	}

	public Engine() {
	}

	/**
	 * 
	 * @return fuelPerKM
	 */
	public int getFuelPerKM() {
		return fuelPerKM;
	}

	/**
	 * 
	 * @param fuelPerKM
	 */
	public void setFuelPerKM(int fuelPerKM) {
		this.fuelPerKM = fuelPerKM;
	}

	/**
	 * 
	 * @return fuelVolume
	 */
	public int getFuelVolume() {
		return fuelVolume;
	}

	/**
	 * 
	 * @param fuelVolume
	 * @return true
	 */
	public boolean setFuelVolume(int fuelVolume) {
		this.fuelVolume = fuelVolume;
		return true;
	}

	@Override
	public String toString() {
		return "Engine [fuelPerKM=" + fuelPerKM + ", fuelVolume=" + fuelVolume
				+ "]";
	}
}
