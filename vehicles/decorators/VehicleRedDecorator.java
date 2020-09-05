package vehicles.decorators;

import vehicles.Colors;
import vehicles.Vehicle;

public class VehicleRedDecorator extends VehicleDecorator
{
    public VehicleRedDecorator(IVehicle decoratedVehicle)
    {
	super(decoratedVehicle);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void draw()
    {
	decoratedVehicle.draw();
	setRedColor(decoratedVehicle);
    }

    private void setRedColor(IVehicle decoratedVehicle)
    {
	((Vehicle)decoratedVehicle).setColor(Colors.Red);
    }
}
