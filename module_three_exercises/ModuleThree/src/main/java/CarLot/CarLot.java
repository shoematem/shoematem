/**
 * @author Matthew Shoemate
 */

package CarLot;

import java.util.List;

public interface CarLot
{
    public Car addCar(String vin, Car car);
    public Car getCar(String vin);
    public List<Car> getCars();
    public void editCar(String vin, Car car);
    public Car removeCar(String vin);
}