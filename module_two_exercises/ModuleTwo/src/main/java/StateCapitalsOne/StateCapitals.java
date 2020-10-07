//@author Matthew Shoemate

package StateCapitalsOne;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StateCapitals
{
    public static void main(String[] args)
    {
        Map<String, String> stateCaps = new HashMap<>();
        
        stateCaps.put("Alabama", "Montgomery");
        stateCaps.put("Alaska", "Juneau");
        stateCaps.put("Arizona", "Phoenix");
        stateCaps.put("Arkansas", "Little Rock");
        stateCaps.put("California", "Sacramento");
        stateCaps.put("Colorado", "Denver");
        stateCaps.put("Connecticut", "Hartford");
        stateCaps.put("Delaware", "Dover");
        stateCaps.put("Florida", "Tallahassee");
        stateCaps.put("Georgia", "Atlanta");
        stateCaps.put("Hawaii", "Honolulu");
        stateCaps.put("Idaho", "Boise");
        stateCaps.put("Illinois", "Springfield");
        stateCaps.put("Indiana", "Indianapolis");
        stateCaps.put("Iowa", "Des Moines");
        stateCaps.put("Kansas", "Topeka");
        stateCaps.put("Kentucky", "Frankfort");
        stateCaps.put("Louisiana", "Baton Rouge");
        stateCaps.put("Maine", "Augusta");
        stateCaps.put("Maryland", "Annapolis");
        stateCaps.put("Massachusetts", "Boston");
        stateCaps.put("Michigan", "Lansing");
        stateCaps.put("Minnesota", "Saint Paul");
        stateCaps.put("Mississippi", "Jackson");
        stateCaps.put("Missouri", "Jefferson City");
        stateCaps.put("Montana", "Helena");
        stateCaps.put("Nebraska", "Lincoln");
        stateCaps.put("Nevada", "Carson City");
        stateCaps.put("New Hampshire", "Concord");
        stateCaps.put("New Jersey", "Trenton");
        stateCaps.put("New Mexico", "Santa Fe");
        stateCaps.put("New York", "Albany");
        stateCaps.put("North Carolina", "Raleigh");
        stateCaps.put("North Dakota", "Bismarck");
        stateCaps.put("Ohio", "Columbus");
        stateCaps.put("Oklahoma", "Oklahoma City");
        stateCaps.put("Oregon", "Salem");
        stateCaps.put("Pennsylvania", "Harrisburg");
        stateCaps.put("Rhode ISland", "Providence");
        stateCaps.put("South Carolina", "Columbia");
        stateCaps.put("South Dakota", "Pierre");
        stateCaps.put("Tennessee", "Nashville");
        stateCaps.put("Texas", "Austin");
        stateCaps.put("Utah", "Salt Lake City");
        stateCaps.put("Vermont", "Montpelier");
        stateCaps.put("Virginia", "Richmond");
        stateCaps.put("Washington", "Olympia");
        stateCaps.put("West Virginia", "Charleston");
        stateCaps.put("Wisconsin", "Madison");
        stateCaps.put("Wyoming", "Cheyenne");
        
        Set<String> states = stateCaps.keySet();
        Set<String> capitals = stateCaps.keySet();
        
        System.out.println("States\n------------------");
        
        for(String s : states)
        {
            System.out.println(s);
        }
        
        System.out.println("\nCapitals\n------------------");
        
        for(String c : capitals)
        {
            System.out.println(stateCaps.get(c));
        }
        
        System.out.println("\nState & Capitals\n------------------");
        
        for(String c: capitals)
        {
            System.out.println(c + " - " + stateCaps.get(c));
        }
    }
}