//@author Matthew Shoemate

package ShapesPerimeters;

public class Triangle extends Shape
{
    private final double area, length, perimeter, width;
    
    public Triangle(double inputLength, double inputWidth)
    {
        length = inputLength;
        width = inputWidth;
        
        area = 0.00;
        perimeter = 0.00;
    }
    
    public Triangle()
    {
        area = 0.00;
        length = 0.00;
        perimeter = 0.00;
        width = 0.00;
    }
    
    public double getLength()
    {
        return length;
    }
    
    public double getWidth()
    {
        return width;
    }
    
    @Override
    public void getArea()
    {
        System.out.println("The area is: ");
    }
    
    @Override
    public void getPerimeter()
    {
        System.out.println("The perimeter is: ");
    }
}