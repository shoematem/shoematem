/**
 * @author Matthew Shoemate
 */

package CarLot;

import java.math.BigDecimal;
import java.util.List;

public interface CarLotService
{
    public Car getACar(String vin);
    public List<Car> getAllCars();
    public List<Car> getCarsByColor(String color);
    public List<Car> getCarsInBudget(BigDecimal maxPrice);
    public List<Car> getCarByMakeAndModel(String make, String model);
    public BigDecimal discountCar(String vin, BigDecimal percentDiscount) throws NoSuchCarException;
    public CarKey sellCar(String vin, BigDecimal cashPaid) throws NoSuchCarException, OverpaidPriceException, UnderpaidPriceException;
}