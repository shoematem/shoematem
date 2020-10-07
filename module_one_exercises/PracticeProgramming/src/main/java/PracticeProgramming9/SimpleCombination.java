//@author Matthew Shoemate

package PracticeProgramming9;

import java.util.Arrays;

public class SimpleCombination
{
    public static void main(String[] args)
    {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 35, 45, 47, 49}; //12 numbers
        int[] secondHalf = {51, 54, 68, 71, 75, 78, 82, 84, 85, 89, 91, 100}; //also 12!
        int[] wholeNumbers = new int[24];
        
        System.arraycopy(firstHalf, 0 , wholeNumbers, 0, firstHalf.length);
        System.arraycopy(secondHalf, 0, wholeNumbers, firstHalf.length, secondHalf.length);
        
        System.out.println("All together now!:");
        System.out.println(Arrays.toString(wholeNumbers));
    }
}