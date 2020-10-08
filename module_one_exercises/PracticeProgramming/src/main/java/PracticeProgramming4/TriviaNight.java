//@author Matthew Shoemate

package PracticeProgramming4;

import java.util.Scanner;

public class TriviaNight
{
    public static void main(String[] args)
    {
        int numCorrect = 0;
        int percentCorrect;
        int answerOne, answerTwo, answerThree;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("It's TRIVIA NIGHT! Are you ready?!\n\nFIRST QUESTION!\nWhat is the Lowest Level Programming Language?");
        System.out.println("1) Source Code\n2) Assembly Language\n3) C#\n4) Machine Code\n");
        System.out.println("YOUR ANSWER: ");
        answerOne = Integer.parseInt(inputReader.nextLine());
        
        if(answerOne == 4)
        {
            System.out.println("Great! That answer is correct!");
            numCorrect++;
        } else
        {
            System.out.println("Darn! That answer is incorrect. The correct answer is 4");
        }
        
        System.out.println("\nSECOND QUESTION!\nWebsite Security CAPTCHA Forms Are Descended From the Work of?");
        System.out.println("1) Grace Hopper\n2) Alan Turing\n3) Charles Babbage\n4) Larry Page\n");
        System.out.println("YOUR ANSWER: ");
        answerTwo = Integer.parseInt(inputReader.nextLine());
        
        if(answerTwo == 2)
        {
            System.out.println("Great! That answer is correct!");
            numCorrect++;
        } else
        {
            System.out.println("Darn! That answer is incorrect. The correct answer is 2");
        }
        
        System.out.println("\nLAST QUESTION!\nWhich of These Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?");
        System.out.println("1) Serenity\n2) The Battlestar Galactica\n3) The USS Enterprise\n4) The Millennium Falcon\n");
        System.out.println("YOUR ANSWER: ");
        answerThree = Integer.parseInt(inputReader.nextLine());
        
        if(answerThree == 3)
        {
            System.out.println("Great! That answer is correct!");
            numCorrect++;
        } else
        {
            System.out.println("Darn! That answer is incorrect. The correct answer is 3");
        }
        
        
        if(numCorrect == 0)
        {
            System.out.println("You got " + numCorrect + ". Better luck next time!\n" + numCorrect + "/3 - " + ((numCorrect / 3.0) * 100) + "%");
        }
        if(numCorrect == 1)
        {
            System.out.println("You got " + numCorrect + ". One is better than none!\n" + numCorrect + "/3 - " + ((numCorrect / 3.0) * 100) + "%");
        }
        if(numCorrect == 2)
        {
            System.out.println("You got " + numCorrect + ". Oh, so close!\n" + numCorrect + "/3 - " + ((numCorrect / 3.0) * 100) + "%");
        }
        if(numCorrect == 3)
        {
            System.out.println("You got " + numCorrect + ". Great job! You got all of the questions correct!\n" + numCorrect + "/3 - "
            + ((numCorrect / 3.0) * 100) + "%");
        }
    }
}