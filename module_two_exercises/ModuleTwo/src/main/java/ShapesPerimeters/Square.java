//@author Matthew Shoemate

package ShapesPerimeters;

public class Square extends Shape
{
    private double area, length, perimeter, width;
    
    public Square(double inputLength, double inputWidth)
    {
        length = inputLength;
        width = inputWidth;
        
        area = 0.00;
        perimeter = 0.00;
    }
    
    public Square()
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
        System.out.println("Area is: ");
    }
    
    @Override
    public void getPerimeter()
    {
        System.out.println("Perimeter is: ");
    }
}