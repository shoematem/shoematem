/*********************************
* The Software Guild
* Copyright (C) 2020 Wiley edu LLC - All Rights Reserved
*********************************/
package com.tsg.unittesting.arrays;

/**
 * @author Matthew Shoemate
 */

public class ArrayExerciseD
{
    /**
     * Given an array of doubles, return the biggest number of the lot, as if the decimal had gone missing!
     *
     * pointFree( [1.1, .22]  ) ->  22
     * pointFree( [ .039 , 20 , .005005 ]  ) ->  5005
     * pointFree( [ -9.9 , -700 , -.5  ]  ) ->  -5
     * 
     * @param numbers
     * @return
     */
    public static int pointFree(double[] numbers)
    {
        throw new UnsupportedOperationException("Code not yet written...!");
        
        /*String[] numberSpl;
        String number;
        int finalNum = 0, holdMaxNum = 0;
        
        for(int i = 0; i < numbers.length; i++)
        {
            numberSpl = Double.toString(numbers[i]).split(".");
            number = numberSpl[0] + numberSpl[1];
            finalNum = Integer.parseInt(number);
            
            if(holdMaxNum < finalNum)
            {
                holdMaxNum = finalNum;
            }
        }
        
        return holdMaxNum;*/
    }   
}