/**
 * StateTaxes - this initializes the variables inside and checks to see if the
 * state entered by the user is valid, or not. It will properly arrange the text
 * just in case it is not entered correctly (but the state they entered is
 * correct - see method FormatState). This class uses enums to get the state name
 * associated with the state abbreviation and vice versa.
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/28/2020
 */

package Flooring.DTO;

public class StateTaxes
{
    private final String stateAbbr;
    private String stateName;
    private String taxRate;
    
    /**
     * StateTaxes - default constructor that initializes the stateAbbr variable
     */
    public StateTaxes()
    {
        this.stateAbbr = "";
    }
    
    /**
     * StateTaxes - constructor that initializes the stateAbbr variable
     * -------------------------------------------------------------------------
     * @param stateAbbr - brings in the stateAbbr that needs to be set
     */
    public StateTaxes(String stateAbbr)
    {
        this.stateAbbr = stateAbbr;
    }
    
    /**
     * toString - overrides the toString() method to return a default string for
     * later use in the program
     * -------------------------------------------------------------------------
     * @return - format string with the state variables in this class
     */
    @Override
    public String toString()
    {
        return stateAbbr + "/" + stateName + "/" + taxRate;
    }

    public void setStateName(String stateName)
    {
        this.stateName = stateName;
    }

    public void setTaxRate(String taxRate)
    {
        this.taxRate = taxRate;
    }
    
    public String getStateAbbr()
    {
        return stateAbbr;
    }
            
    public String getStateName()
    {
        return stateName;
    }
    
    public String getTaxRate()
    {
        return taxRate;
    }
    
    /**
     * States - a private enum to be used in this class. It sets the constants to
     * be each of the 50 states plus DC. Each state has a value of its associated
     * state abbreviation and name
     */
    private enum States
    {
        AK("AK", "Alaska"), AL("AL", "Alabama"), AR("AR", "Arkansas"), AZ("AZ", "Arizona"),
        CA("CA", "California"), CO("CO", "Colorado"), CT("CT", "Connecticut"), DC("DC", "District of Columbia"),
        DE("DE", "Delaware"), FL("FL", "Florida"), GA("GA", "Georgia"), HI("HI", "Hawaii"), IA("IA", "Iowa"),
        ID("ID", "Idaho"), IL("IL", "Illinois"), IN("IN", "Indiana"), KS("KS", "Kansas"), KY("KY", "Kentucky"),
        LA("LA", "Louisiana"), MA("MA", "Massachusetts"), MD("MD", "Maryland"), ME("ME", "Maine"),
        MI("MI", "Michigan"), MN("MN", "Minnesota"), MO("MO", "Missouri"), MS("MS", "Mississippi"),
        MT("MT", "Montana"), NC("NC", "North Carolina"), ND("ND", "North Dakota"), NE("NE", "Nebraska"),
        NH("NH", "New Hampshire"), NJ("NJ", "New Jersey"), NM("NM", "New Mexico"), NV("NV", "Nevada"),
        NY("NY", "New York"), OH("OH", "Ohio"), OK("OK", "Oklahoma"), OR("OR", "Oregon"),
        PA("PA", "Pennsylvania"), RI("RI", "Rhode Island"), SC("SC", "South Carolina"), SD("SD", "South Dakota"),
        TN("TN", "Tennessee"), TX("TX", "Texas"), UT("UT", "Utah"), VA("VA", "Virginia"), VT("VT", "Vermont"),
        WA("WA", "Washington"), WI("WI", "Wisconsin"), WV("WV", "West Virginia"), WY("WY", "Wyoming");
    
        private final String sAbbr, sName;
        
        /**
         * States - constructor to set the state abbreviation and name
         * ---------------------------------------------------------------------
         * @param sAbbr - the state abbreviation needed to be checked
         * @param sName - the state name needed to be checked
         */
        States(String sAbbr, String sName)
        {
            this.sAbbr = sAbbr;
            this.sName = sName;
        }
        
        /**
         * toString - overrides the toString() for enum
         * ---------------------------------------------------------------------
         * @return - formats how the enum's value needs to be passed
         */
        @Override
        public String toString()
        {
            return sAbbr + "," + sName;
        }
    }
    
    /**
     * CheckStateExists - checks if the entered state from the user exists,
     * or not. It will return "UNK" if it is not one of the 50 + 1 (DC) states.
     * This is not the check if the state is in the StateTaxes file
     * -------------------------------------------------------------------------
     * @param userInput - input from the user (state abbreviation or name)
     * @return - state abbreviation, state name
     */
    public String CheckStateExists(String userInput)
    {
        String state = "UNK"; //will return an unknown state - user did not input a known US state
        userInput = FormatState(userInput);
        
        if(userInput.length() >= 2)
        {
            for(States getState : States.values())
            {
                if(getState.toString().contains(userInput))
                {
                    state = getState.toString();
                }
            }
        }
        
        return state;
    }
    
    /**
     * FormatState - formats the user's input. It checks if it is an abbreviation
     * and if so, uppercase the string and exit. If it is the name, grab the string
     * and lowercase the entire string and replace all spaces with blanks, to make
     * a state with 2 words into 1 word. Then format the string with the appropriate
     * spaces needed. It does this just in case someone enters (N ew York or Michi gan).
     * -------------------------------------------------------------------------
     * @param state - the state from the user
     * @return - the formatted state
     */
    public String FormatState(String state)
    {
        String formatState = state;
        
        if(formatState.length() == 2)
        {
            formatState = formatState.toUpperCase();
        } else
        {
            formatState = formatState.toLowerCase().replaceAll(" ", ""); //replace all spaces with a blank

            if(formatState.contains("new"))
            {
                //grab the string "new" and put a space after it
                formatState = formatState.substring(0, 2) + " " + formatState.substring(3);
            } else if(formatState.contains("rhode") || formatState.contains("north") || formatState.contains("south"))
            {
                //grab the string "rhode" and put a space after it
                formatState = formatState.substring(0, 4) + " " + formatState.substring(5);
            } else if(formatState.contains("district"))
            {
                //grab the string "district" and put a space after it
                formatState = formatState.substring(0, 7) + " " + formatState.substring(8, 9) + " " + formatState.substring(10);
            } else if(formatState.contains("west"))
            {
                //grab the string "west" and put a space after it
                formatState = formatState.substring(0, 3) + " " + formatState.substring(4);
            }

            char[] stateChar = formatState.toCharArray(); //convert the formatted string to a character array
            boolean previousCharIsLetter = false;
            formatState = "";
            
            /**
             * This for loop loops through the character array. It attempts to find the start of a word and capitalize it.
             * If a word is after a space, it is a new word. If not, it will keep looping until the character array has been
             * completely gone through to reformat the string (ex: new york -> New York .. if it were somehow nEw yOrK -> New York).
             */
            for(int i = 0; i < stateChar.length; i++)
            {
                if(!previousCharIsLetter && Character.isLetter(stateChar[i]))
                {
                    stateChar[i] = Character.toUpperCase(stateChar[i]);
                    previousCharIsLetter = true;
                } else if(Character.isWhitespace(stateChar[i])) //if the character is a space
                {
                    previousCharIsLetter = false;
                }
                
                formatState += stateChar[i];
            }
        }
        
        return formatState;
    }
}