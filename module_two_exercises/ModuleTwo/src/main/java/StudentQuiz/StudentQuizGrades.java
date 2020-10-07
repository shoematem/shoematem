/**
 * @author Matthew Shoemate
 */

package StudentQuiz;

import java.util.Scanner;
import UserIOInter.UserIO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentQuizGrades
{
    public static void main(String[] args)
    {
        int userChoice = 0;
        UserIO userIO = new StudentQuizConfig();
        StudentQuizConfig studentClass = new StudentQuizConfig();
        Map<String, List<Integer>> students = new HashMap<>();
        Scanner userInput = new Scanner(System.in);
        
        while(userChoice != 10)
        {
            userIO.print("Choose what you would like to do.");
            
            userIO.print("1. Add Student");
            userIO.print("2. Remove Student");
            userIO.print("3. Add Student Quiz Scores");
            userIO.print("4. View a List of Students");
            userIO.print("5. View a List of Quiz Scores");
            userIO.print("6. View the Average Quiz Score for a Given Student.");
            userIO.print("7. View the Average Quiz Score for the Class");
            userIO.print("8. View the Highest Score in the Class");
            userIO.print("9. View the Lowest Score in the Class");
            userIO.print("10. Exit");
            
            try
            {
                userChoice = Integer.parseInt(userInput.nextLine());
            } catch(NumberFormatException e)
            {
                userIO.print("\nYou did not input a whole number. Please choose one of the options with a whole number.\n");
                continue;
            }
            
            switch(userChoice)
            {
                case 1:
                    students = studentClass.AddStudent(students);
                    
                    break;
                case 2:
                    if(students.isEmpty())
                    {
                        userIO.print("There are no students to remove!");
                    } else
                    {
                        studentClass.RemoveStudent(students);
                    }
                    
                    break;
                case 3:
                    if(students.isEmpty())
                    {
                        userIO.print("There are no students to add a quiz score to.");
                    } else
                    {
                        studentClass.AddQuizScores(students);
                    }
                    break;
                case 4:
                    studentClass.ListStudents(students);
                    break;
                case 5:
                    studentClass.ListQuizScores(students);
                    break;
                case 6:
                    studentClass.AverageQuizScore(students);
                    break;
                case 7:
                    studentClass.AverageClassScore(students);
                    break;
                case 8:
                    studentClass.HighestClassScore(students);
                    break;
                case 9:
                    studentClass.LowestClassScore(students);
                    break;
                case 10:
                    userIO.print("\nThank you for using this program!\n");
                    break;
                default:
                    userIO.print("\nYou did not input a number that was specified. Try again.\n");
                    break;
            }
            
        }
        
    }
}