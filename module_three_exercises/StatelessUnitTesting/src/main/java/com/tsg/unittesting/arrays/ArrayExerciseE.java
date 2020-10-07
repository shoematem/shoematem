/*********************************
* The Software Guild
* Copyright (C) 2020 Wiley edu LLC - All Rights Reserved
*********************************/
package com.tsg.unittesting.arrays;

/**
 * @author Matthew Shoemate
 */

public class ArrayExerciseE
{
    /**
     * Given an array of words turn it into a single camelCased phrase.
     * Lower case the first word, capitalize the first letter (but only the first) of the rest.
     *
     * camelCaseIt( {"llama", "llama", "duck"}  ) ->  "llamaLlamaDuck"
     * camelCaseIt( {"lambs", "eat", "oats", "and", "does", "eat", "oats"}  ) ->  "lambsEatOatsAndDoesEatOats"
     * camelCaseIt( {"DO", "OR", "DO", "NOT", "THERE", "IS", "NO", "TRY"}  ) ->  "doOrDoNotThereIsNoTry"
     * @param words
     * @return String camelCased phrase
     */
    public static String camelCaseIt(String[] words)
    {
        throw new UnsupportedOperationException("Code not yet written...!");
        
        /*String camelCase = "";
        
        for(int i = 0; i < words.length; i++)
        {
            if(i == 0)
            {
                camelCase = words[i].toLowerCase();
            } else
            {
                camelCase += words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
            }
        }
        
        return camelCase;*/
    }
}