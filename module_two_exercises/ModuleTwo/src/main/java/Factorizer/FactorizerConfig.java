//@author Matthew Shoemate

package Factorizer;

public class FactorizerConfig
{
    private final int number;
    private final int[] factors;
    private final boolean isPrime, isPerfect;
    
    public FactorizerConfig(int inputNumber)
    {
        number = inputNumber;
        int[] tempFactors = new int[number]; //create a temporary array
        int count = 1, sum = 0;
        tempFactors[0] = 1;
        
        for(int i = 2; i <= (inputNumber / 2); i++) //you cannot have another factor if the number is > half the number
        {
            if((inputNumber % i) == 0)
            {
                tempFactors[count] = i; //assign the count number (index of current point in the array) to the current number of i
                count++; //increment count
            }
        }
        
        count++; //add one more to count because we know the number itself is a factor
        int[] result = new int[count];
        System.arraycopy(tempFactors, 0, result, 0, result.length);
        result[result.length - 1] = number;
        factors = result;
        
        for(int i = 0; i < factors.length; i++)
        {
            sum += factors[i];
        }
        
        isPrime = (factors.length == 2);
        isPerfect = (sum == number);
    }
    
    public FactorizerConfig()
    {
        number = 0;
        factors = new int[]{0};
        isPrime = false;
        isPerfect = false;
    }
    
    public boolean getPrime()
    {
        return isPrime;
    }
    
    public boolean getPerfect()
    {
        return isPerfect;
    }
    
    public int getNumber()
    {
        return number;
    }
    
    public int[] getFactors()
    {
        return factors;
    }
    
    @Override
    public String toString()
    {
        String formatOutput;
        
        formatOutput = "The factors of " + number + " are: \n";
        
        for(int i = 0; i < factors.length; i++)
        {
            formatOutput += factors[i] + " ";
        }
        
        formatOutput += "\n" + number + " has " + factors.length + " factors.\n";
        
        if(getPrime())
        {
            formatOutput += number + " is a prime number.\n";
            formatOutput += number + " is not a perfect number.\n";
        } else
        {
            formatOutput += number + " is not a prime number.\n";
            
            if(getPerfect())
            {
                formatOutput += number + " is a perfect number.\n";
            } else
            {
                formatOutput += number + " is not a perfect number.\n";
            }
        }
        
        return formatOutput;
    }
}