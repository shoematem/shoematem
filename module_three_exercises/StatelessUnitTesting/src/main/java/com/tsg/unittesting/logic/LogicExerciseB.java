/*********************************
* The Software Guild
* Copyright (C) 2020 Wiley edu LLC - All Rights Reserved
*********************************/
package com.tsg.unittesting.logic;

/**
 * @author Matthew Shoemate
 */

public class LogicExerciseB
{
    /**
     * Given a number, return the 'place rank' word associated with it. I.e. pretend you're ranking folks
     * running in a race from the order they arrived at the finish line. Assume nonzero, positive inputs! 
     * Also, start by going up to 100, but stretch to more if you can!
     *
     * Ex:
     * placeOf( 1 ) ->   "1st"
     * placeOf( 3 ) ->   "3rd"
     * placeOf( 22 ) ->   "22nd"
     *
     * @param place
     * @return String
     */
    public String placeOf(int place)
    {
        throw new UnsupportedOperationException("Code not yet written...!");
        
        /*String number = Integer.toString(place);
        String lastChar = number.substring(number.length() - 1);
        
        if(lastChar.equals("1"))
        {
            number = number + "st";
        } else if(lastChar.equals("2"))
        {
            number = number + "nd";
        } else if(lastChar.equals("3"))
        {
            number = number + "rd";
        } else
        {
            number = number + "th";
        }
        
        return number;*/
    }
}