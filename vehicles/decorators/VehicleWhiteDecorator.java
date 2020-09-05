package vehicles.decorators;

import vehicles.Colors;
import vehicles.Vehicle;

public class VehicleWhiteDecorator extends VehicleDecorator
{
    public VehicleWhiteDecorator(IVehicle decoratedVehicle)
    {
	super(decoratedVehicle);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void draw()
    {
	decoratedVehicle.draw();
	setWhiteColor(decoratedVehicle);
    }

    private void setWhiteColor(IVehicle decoratedVehicle)
    {
	((Vehicle)decoratedVehicle).setColor(Colors.White);
    }
}

