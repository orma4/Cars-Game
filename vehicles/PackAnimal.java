package vehicles;

import graphics.IAnimal;
import vehicles.Carriage;

public class PackAnimal implements IAnimal, Cloneable {
	private static final String nameOfAnimal = "Pack Animal";
	private int energy = 1000;
	private Carriage carriage;
	private static final int maxenergy = 1000;

	public PackAnimal() {
	}

	public int getEnergy() {
		return energy;
	}

	public boolean setEnergyPerKM(double distance) {
		this.energy = energy - 20 * (int) distance;
		return false;
	}

	public String getNameofanimal() {
		return nameOfAnimal;
	}

	@Override
	public int getSpeed() {
		return carriage.getSpeed();
	}

	@Override
	public int getFuelConsumption() {
		return 0;
	}

	@Override
	public String getAnimalName() {
		return "Pack Animal";
	}

	@Override
	public boolean eat() {
		if (maxenergy >= this.energy) {
			this.energy = maxenergy;
			return true;
		}
		return false;
	}

	@Override
	public String getVehicleName() {
		return null;
	}

	@Override
	public void move(Point point) {

	}

	@Override
	public int getDurability() {
		return 0;
	}
}
