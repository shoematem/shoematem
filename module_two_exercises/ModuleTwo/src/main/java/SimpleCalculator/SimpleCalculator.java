//@author Matthew Shoemate

package SimpleCalculator;

public class SimpleCalculator
{
    private final double addition, subtraction, multiplication, division;
    private final int operationChoice;
    
    public SimpleCalculator(double operandOne, double operandTwo, int userChoice)
    {
        double holdAdd = 0.00, holdSub = 0.00, holdMult = 0.00, holdDiv = 0.00;
        operationChoice = userChoice;
        
        switch(operationChoice)
        {
            case 1:
                holdAdd = operandOne + operandTwo;
                break;
            case 2:
                holdSub = operandOne - operandTwo;
                break;
            case 3:
                holdMult = operandOne * operandTwo;
                break;
            case 4:
                holdDiv = operandOne / operandTwo;
                break;
            default:
                break;
        }
        
        addition = holdAdd;
        subtraction = holdSub;
        multiplication = holdMult;
        division = holdDiv;
    }
    
    public SimpleCalculator()
    {
        addition = 0.00;
        subtraction = 0.00;
        multiplication = 0.00;
        division = 0.00;
        operationChoice = 0;
    }
    
    public double getAddition()
    {
        return addition;
    }
    
    public double getSubstration()
    {
        return subtraction;
    }
    
    public double getMultiplication()
    {
        return multiplication;
    }
    
    public double getDivision()
    {
        return division;
    }
    
    public int getOperationChoice()
    {
        return operationChoice;
    }
    
    @Override
    public String toString()
    {
        String formatOutput = "The answer is ";
        
        switch(operationChoice)
        {
            case 1:
                formatOutput += addition + "\n";
                break;
            case 2:
                formatOutput += subtraction + "\n";
                break;
            case 3:
                formatOutput += multiplication + "\n";
                break;
            case 4:
                formatOutput += division + "\n";
                break;
            default:
                break;
        }
        
        return formatOutput;
    }
}