package vehicles.decorators;

import vehicles.Colors;
import vehicles.Vehicle;

public class VehicleLightsDecorator extends VehicleDecorator
{
    public VehicleLightsDecorator(IVehicle decoratedVehicle)
    {
	super(decoratedVehicle);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void draw()
    {
	decoratedVehicle.draw();
	switchLights(decoratedVehicle);
    }

    private void switchLights(IVehicle decoratedVehicle)
    {
	((Vehicle)decoratedVehicle).setLights(!((Vehicle)decoratedVehicle).isLights());
    }
}


