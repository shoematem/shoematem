//@author Matthew Shoemate

package InterestCalculator;

public class InterestCalculatorConfig
{
    private final double amountInvest, interestRate;
    private final int numYears;
    private final String interestEarned;
    
    public InterestCalculatorConfig(double inAmountInvest, int inNumYears, double inInterestRate)
    {
        numYears = inNumYears;
        double earnedMoney, principleEarned, holdAmountInvest, holdInterestRate;
        String formatOutput = "";
        
        holdAmountInvest = inAmountInvest;
        holdInterestRate = inInterestRate;
        
        for(int i = 1; i < numYears; i++)
        {
            formatOutput += "\nYear = " + i;
            formatOutput += "\nBegan with " + holdAmountInvest;
            
            earnedMoney = InterestEarned(holdAmountInvest, holdInterestRate);
            principleEarned = earnedMoney - holdAmountInvest;
            principleEarned = (double) Math.round(principleEarned * 100) / 100;
            
            formatOutput += "\nEarned " + principleEarned;
            formatOutput += "\nEnded with " + earnedMoney;
            
            holdAmountInvest = earnedMoney;
        }
        
        amountInvest = holdAmountInvest;
        interestRate = holdInterestRate;
        interestEarned = formatOutput;
    }
    
    public InterestCalculatorConfig()
    {
        amountInvest = 0.00;
        interestRate = 0.00;
        numYears = 0;
        interestEarned = "";
    }
    
    public double InterestEarned(double inAmountInvest, double inInterestRate)
    {
        double total = 0;
        
        inInterestRate = inInterestRate / 4;
        
        for(int i = 1; i <= 4; i++)
        {
            total = inAmountInvest * (1 + (inInterestRate / 100));
            inAmountInvest = total;
        }
        
        total = (double) Math.round(total * 100) / 100;
        return total;
    }
    
    public double getAmountInvest()
    {
        return amountInvest;
    }
    
    public double getInterestRate()
    {
        return interestRate;
    }
    
    public int getNumYears()
    {
        return numYears;
    }
    
    public String getInterestEarned()
    {
        return interestEarned;
    }
    
    @Override
    public String toString()
    {   
        return interestEarned;
    }
}