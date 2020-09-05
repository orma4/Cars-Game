package vehicles;

import javax.swing.JSlider;

import graphics.CityPanel;
import vehicles.decorators.*;


public class VehicleFactory
{
    private static Vehicle vehicle = null;

    public static Vehicle getVehicle(VehicleType vehicleType, Colors checkedColor)
    {
	vehicle = null;
	switch (vehicleType)
	{
	    case BenzineCar:
		vehicle = new Car(new BenzineEngine(40), checkedColor);
		break;
	    case SolarCar:
		vehicle = new Car(new SolarEngine(40), checkedColor);
		break;
	    case Carriage:
		vehicle = new Carriage(checkedColor);
		break;
	}
	
	//setColor(checkedColor);
	return  vehicle;
    }

    public static Bike getBike(Colors checkedColor, int gearValue)
    {
	vehicle = new Bike(gearValue, checkedColor);
	//setColor(checkedColor);
	return (Bike) vehicle;
    }
    
//    public static void setColor(Colors checkedColor)
//    {
//	switch (checkedColor)
//	{
//	    case Green:
//		vehicle = new VehicleGreenDecorator(vehicle);
//		break;
//	    case White:
//		vehicle = new VehicleWhiteDecorator(vehicle);
//		break;
//	    case Silver:
//		vehicle = new VehicleSilverDecorator(vehicle);
//		break;
//	    case Red:
//		vehicle = new VehicleRedDecorator(vehicle);
//		break;
//	}
//	vehicle.draw();
//    }
    

}
