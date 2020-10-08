//@author Matthew Shoemate

package PracticeProgramming7;

public class ForByFor
{
    public static void main(String[] args)
    {
        for(int i = 0; i < 3; i++)
        {
            System.out.print("|");
            
            for(int j = 0; j < 3; j++)
            {        
                for(int k = 0; k < 3; k++)
                {   
                    switch(j)
                    {
                        case 0:
                        case 2:
                            if(i == 1)
                            {
                                System.out.print("@");
                            } else
                            {
                                System.out.print("*");
                            }
                            
                            break;
                        case 1:
                            if(i != 1)
                            {
                                System.out.print("$");
                            } else
                            {
                                System.out.print("#");
                            }
                            
                            break;
                    }
                }
                
                System.out.print("|");
            }
            
            System.out.println("");
        }
    }
}