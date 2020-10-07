/**
 * @author Matthew Shoemate
 */

package HappyLlamas;

public class HappyLlamas
{
    
    /**
     * A method to determine if the provided trampolines will result in happy llamas.
     * 
     * When llamas get together they like to bounce on trampolines.
     * However, llamas are very particular about the proper number of tramplines,
     * are are usually only happy if they are between 24 to 42 (inclusive!).
     * This only changes if the trampolines are made of ultra-bouncey NASA fabric.
     * In those cases, while they still require at LEAST 24, the llamas figure
     * the more trampolines the better!
     * 
     * return true if the llamas will be happy with their tramplines,
     * or false otherwise
     * 
     * @param ultraBouncy - true if trampolines are made of UltraBouncy NASA fabric.
     * @param numTrampolines - the number of trampolines
     * @return boolean indicating if the llama's are happy
     */
    public static boolean areTheLlamasHappy(boolean ultraBouncy, int numTrampolines)
    {
        boolean isHappy = false;
        
        if(numTrampolines >= 24)
        {
            if(ultraBouncy)
            {
                isHappy = true;
            } else if(numTrampolines <= 42)
            {
                isHappy = true;
            }
        }
        
        return isHappy;
    }
}