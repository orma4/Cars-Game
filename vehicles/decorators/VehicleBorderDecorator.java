package vehicles.decorators;

import vehicles.Colors;
import vehicles.Vehicle;

public class VehicleBorderDecorator extends VehicleDecorator
{

    public VehicleBorderDecorator(IVehicle decoratedVehicle)
    {
	super(decoratedVehicle);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void draw()
    {
	decoratedVehicle.draw();
	switchBorder(decoratedVehicle);
    }

    private void switchBorder(IVehicle decoratedVehicle)
    {
	((Vehicle)decoratedVehicle).setBorder(!((Vehicle)decoratedVehicle).isBorder());
    }

    
}
