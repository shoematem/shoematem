//@author Matthew Shoemate

package ShapesPerimeters;

public abstract class Shape
{
    public static void main(String[] args)
    {
        Square newSquare = new Square(2, 2);
        Rectangle newRectangle = new Rectangle(2, 2);
        Triangle newTriangle = new Triangle(2, 2);
        Circle newCircle = new Circle(2, 2);
        
        newRectangle.getArea();
    }
    
    protected void getArea()
    {}
    
    protected void getPerimeter()
    {}
}