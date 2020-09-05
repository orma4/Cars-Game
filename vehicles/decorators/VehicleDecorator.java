package vehicles.decorators;

public abstract class VehicleDecorator implements IVehicle
{
    protected IVehicle decoratedVehicle;

    public VehicleDecorator(IVehicle decoratedVehicle)
    {
	this.decoratedVehicle = decoratedVehicle;
    }

    @Override
    public void draw()
    {
	decoratedVehicle.draw();
	
    }
    
    
    
    
}
