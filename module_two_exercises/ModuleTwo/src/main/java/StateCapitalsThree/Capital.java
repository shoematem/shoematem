//@author Matthew Shoemate

package StateCapitalsThree;

import java.util.ArrayList;
import java.util.List;

public class Capital
{
    private String name;
    private int population, squareMileage;
    
    public Capital()
    {
        name = "";
        population = 0;
        squareMileage = 0;
    }
    
    public void PopulationLimit(Object[] capValues, String userChoice)
    {
        int popLimit = Integer.parseInt(userChoice);
        List<String> capsOverLimit = new ArrayList<>();
        String capStr;
        String[] capObj;

        for(int i = 0; i < capValues.length; i++)
        {
            capStr = capValues[i].toString();
            capObj = capStr.split("/");

            name = capObj[0];
            population = Integer.parseInt(capObj[1]);
            squareMileage = (int) Double.parseDouble(capObj[2]);
            
            if(popLimit < population)
            {
                capsOverLimit.add(name + " | " + population + " | " + squareMileage);
            }
        }
        
        if(!capsOverLimit.isEmpty())
        {
            System.out.println("Listing capitals with populations greater than " + popLimit + "\n");

            for(int i = 0; i < capsOverLimit.size(); i++)
            {
                System.out.println(capsOverLimit.get(i));
            }
        }
    }
    
    public void CitySquareMileage(Object[] capValues, String userChoice)
    {
        int sqMileageMax = Integer.parseInt(userChoice);
        List<String> capsUnderLimit = new ArrayList<>();
        String capStr;
        String[] capObj;
        
        for(int i = 0; i < capValues.length; i++)
        {
            capStr = capValues[i].toString();
            capObj = capStr.split("/");
            
            name = capObj[0];
            population = Integer.parseInt(capObj[1]);
            squareMileage = (int) Double.parseDouble(capObj[2]);
            
            if(sqMileageMax > squareMileage)
            {
                capsUnderLimit.add(name + " | " + population + " | " + squareMileage);
            }
        }
        
        if(!capsUnderLimit.isEmpty())
        {
            System.out.println("List capitals with areas less than " + squareMileage);
            
            for(int i = 0; i < capsUnderLimit.size(); i++)
            {
                System.out.println(capsUnderLimit.get(i));
            }
        }
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getPopulation()
    {
        return population;
    }
    
    public int getSquareMileage()
    {
        return squareMileage;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setPopulation(int population)
    {
        this.population = population;
    }
    
    public void setSquareMileage(int squareMileage)
    {
        this.squareMileage = squareMileage;
    }
}