//@author Matthew Shoemate

package StudentQuiz;

import UserIOInter.UserIO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class StudentQuizConfig implements UserIO
{
    private final Scanner userInput;
    private int intNumber;
    private double doubleNumber;
    private float floatNumber;
    private long longNumber;
    private String stringInput;
    
    public StudentQuizConfig()
    {
        this.userInput = new Scanner(System.in);
        intNumber = 0;
        doubleNumber = 0;
        floatNumber = 0;
        longNumber = 0;
        stringInput = "";
    }
    
    public Map<String, List<Integer>> AddStudent(Map<String, List<Integer>> students)
    {
        List<Integer> zero = new ArrayList<>();
        String goodInput = "y";
        
        if(students.isEmpty())
        {
            students = new HashMap<>();
        }
        
        do
        {
            if(goodInput.equals("y"))
            {
                print("\nPlease enter a student.");
                students.put(userInput.nextLine(), zero);
            }
            
            print("\nWould you like to enter another student? (y/n)");
            goodInput = CheckInput();
        } while(!goodInput.equals("n"));
        
        return students;
    }
    
    public Map<String, List<Integer>> AddQuizScores(Map<String, List<Integer>> students)
    {
        String goodInput = "y", userChoice = "";
        
        do
        {
            List<Integer> quizScores = new ArrayList<>();
            List<Integer> studentQuizzes = new ArrayList<>();
            Set<String> studentKeys = students.keySet();
            
            if(goodInput.equals("y"))
            {
                print("\nWhat student would you like to enter a quiz score to?");
                userChoice = userInput.nextLine();
                
                if(!students.containsKey(userChoice))
                {
                    print("Student " + userChoice + " is not in the students list.");
                    continue;
                }
                
                if(students.containsKey(userChoice))
                {
                    for(String sK : studentKeys)
                    {
                        studentQuizzes = students.get(sK);
                    }
                    
                    if(!studentQuizzes.isEmpty())
                    {
                        quizScores = students.get(userChoice);
                    }
                }
                
                do
                {
                    print("\nPlease enter a quiz score (Just the number - ex: 95).");
                    
                    try
                    {
                        quizScores.add(Integer.parseInt(userInput.nextLine()));
                        students.put(userChoice, quizScores);
                    } catch(NumberFormatException e)
                    {
                        print("\nYou did not enter a whole number. Please try again.");
                        continue;
                    }
                    
                    print("\nWould you like to enter another quiz score? (y/n)");
                    goodInput = CheckInput();
                } while(!goodInput.equals("n"));
            }
            
            print("\nWould you like to enter a quiz score for a different student? (y/n)");
            goodInput = CheckInput();
        } while(!goodInput.equals("n"));

        return students;
    }
    
    public void RemoveStudent(Map<String, List<Integer>> students)
    {
        String goodInput = "y", userChoice = "";
        
        do
        {
            if(goodInput.equals("y"))
            {
                print("\nWhat student would you like to remove?");
                userChoice = userInput.nextLine();
            }
            
            if(students.containsKey(userChoice))
            {
                students.remove(userChoice);
                print("Student is successfully removed.");
            } else
            {
                print("Student " + userChoice + " is not in the students list.");
                continue;
            }
            
            if(!students.isEmpty())
            {
                print("\nWould you like to remove another student? (y/n)");
                goodInput = CheckInput();
            } else
            {
                goodInput = "n";
            }
        } while(!goodInput.equals("n"));
    }
    
    public void ListStudents(Map<String, List<Integer>> studentList)
    {
        Set<String> studentKeys = studentList.keySet();
        
        for(String sK : studentKeys)
        {
            print("\n" + sK);
        }
    }
    
    public void ListQuizScores(Map<String, List<Integer>> studentList)
    {
        Set<String> studentKeys = studentList.keySet();
        
        for(String sK : studentKeys)
        {
            print("\n" + sK + " " + studentList.get(sK));
        }
    }
    
    public void AverageQuizScore(Map<String, List<Integer>> students)
    {
        String goodInput = "y", userChoice = "";
        List<Integer> quizScores = new ArrayList<>();
        Set<String> studentKeys = students.keySet();

        do
        {
            if(goodInput.equals("y"))
            {
                print("\nWhat student would you like to enter a quiz score to?");
                userChoice = userInput.nextLine();

                if(!students.containsKey(userChoice))
                {
                    print("\nStudent " + userChoice + " is not in the students list.");
                    continue;
                }

                if(students.containsKey(userChoice))
                {
                    double average = 0;
                    
                    for(String sK : studentKeys)
                    {
                        quizScores = students.get(sK);
                        print(quizScores.toString());
                    }
                    
                    for(int i = 0; i < quizScores.size(); i++)
                    {
                        average += quizScores.get(i);
                    }
                    
                    average = average / quizScores.size();
                    
                    print("\n" + userChoice + "'s average: " + Double.toString(average));
                }
            }
            
            print("Would you like to get another student's average quiz score? (y/n)");
            goodInput = CheckInput();
        } while(!goodInput.equals("n"));
    }
    
    public void AverageClassScore(Map<String, List<Integer>> students)
    {
        double average = 0;
        int j = 0;
        List<Integer> quizScores;
        Set<String> studentKeys = students.keySet();
        
        for(String sK : studentKeys)
        {
            quizScores = students.get(sK);
            
            for(int i = 0; i < quizScores.size(); i++)
            {
                average += quizScores.get(i);
                j++;
            }
        }
        
        average = average / j;
        print("Average class score is " + average);
    }
    
    public void HighestClassScore(Map<String, List<Integer>> students)
    {
        int max = 0;
        List<Integer> quizScores;
        Set<String> studentKeys = students.keySet();
        
        for(String sK : studentKeys)
        {
            quizScores = students.get(sK);
            
            for(int i = 0; i < quizScores.size(); i++)
            {
                if(max < quizScores.get(i))
                {
                    max = quizScores.get(i);
                }
            }
        }
        
        print("Highest score in the class is " + max);
    }
    
    public void LowestClassScore(Map<String, List<Integer>> students)
    {
        int min = 1000;
        List<Integer> quizScores;
        Set<String> studentKeys = students.keySet();
        
        for(String sK : studentKeys)
        {
            quizScores = students.get(sK);
            
            for(int i = 0; i < quizScores.size(); i++)
            {
                if(min > quizScores.get(i))
                {
                    min = quizScores.get(i);
                }
            }
        }
        print("Lowest score in the class is " + min);
    }
    
    private String CheckInput()
    {
        stringInput = userInput.nextLine();
        
        if(!stringInput.equals("n") && !stringInput.equals("y"))
        {
            print("\nYou did not enter a y or n, please try again.");
        }
        
        return stringInput;
    }
    
    public int getIntNumber()
    {
        return intNumber;
    }
    
    public double getDoubleNumber()
    {
        return doubleNumber;
    }
    
    public float getFloatNumber()
    {
        return floatNumber;
    }
    
    public long getLongNumber()
    {
        return longNumber;
    }
    
    public String getStringInput()
    {
        return stringInput;
    }
    
    public void setStringInput(String stringInput)
    {
        this.stringInput = stringInput;
    }
    
    public void setIntNumber(int number)
    {
        this.intNumber = number;
    }
    
    public void setDoubleNumber(double number)
    {
        this.doubleNumber = number;
    }
    
    public void setFloatNumber(float number)
    {
        this.floatNumber = number;
    }
    
    public void setLongNumber(long number)
    {
        this.longNumber = number;
    }
    
    @Override
    public void print(String message)
    {
        System.out.println(message);
    }
    
    @Override
    public String readString(String prompt)
    {
        System.out.println("\n" + prompt);
        
        return stringInput;
    }
    
    @Override
    public int readInt(String prompt)
    {
        System.out.println("\n" + prompt);
        
        return intNumber;
    }
    
    @Override
    public int readInt(String prompt, int min, int max)
    {
        System.out.println("\n" + prompt);

        return intNumber;
    }
    
    @Override
    public double readDouble(String prompt)
    {
        System.out.println("\n" + prompt);
        
        return doubleNumber;
    }
    
    @Override
    public double readDouble(String prompt, double min, double max)
    {
        System.out.println("\n" + prompt);
        
        return doubleNumber;
    }
    
    @Override public float readFloat(String prompt)
    {
        System.out.println("\n" + prompt);
        
        return floatNumber;
    }
    
    @Override public float readFloat(String prompt, float min, float max)
    {
        System.out.println("\n" + prompt);
       
        return floatNumber;
    }
    
    @Override public long readLong(String prompt)
    {
        System.out.println("\n" + prompt);
        
        return longNumber;
    }
    
    @Override
    public long readLong(String prompt, long min, long max)
    {
        System.out.println("\n" + prompt);
       
        return longNumber;
    }
}