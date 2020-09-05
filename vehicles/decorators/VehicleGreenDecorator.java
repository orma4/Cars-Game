package vehicles.decorators;

import java.awt.Color;

import vehicles.Colors;
import vehicles.Vehicle;

public class VehicleGreenDecorator extends VehicleDecorator
{

    public VehicleGreenDecorator(IVehicle decoratedVehicle)
    {
	super(decoratedVehicle);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void draw()
    {
	decoratedVehicle.draw();
	setGreenColor(decoratedVehicle);
    }

    private void setGreenColor(IVehicle decoratedVehicle)
    {
	((Vehicle)decoratedVehicle).setColor(Colors.Green);
    }
}
