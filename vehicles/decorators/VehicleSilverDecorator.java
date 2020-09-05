package vehicles.decorators;

import vehicles.Colors;
import vehicles.Vehicle;

public class VehicleSilverDecorator extends VehicleDecorator
{
    public VehicleSilverDecorator(IVehicle decoratedVehicle)
    {
	super(decoratedVehicle);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void draw()
    {
	decoratedVehicle.draw();
	setSilverColor(decoratedVehicle);
    }

    private void setSilverColor(IVehicle decoratedVehicle)
    {
	((Vehicle)decoratedVehicle).setColor(Colors.Silver);
    }
}


