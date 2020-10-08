/**
 * @author Matthew Shoemate
 */

package BirthdayCalculator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class BirthdayCalculator
{
    public static void main(String[] args)
    {
        String date;
        String[] dateArr;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String currentDay = LocalDate.now().format(formatter);
        LocalDate dayToday = LocalDate.parse(currentDay, formatter);
        LocalDate userOrigBDay, userBDayNext, past;
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("Welcome to the Magical BirthDAY Calculator!\n");
        System.out.println("What's your birthday? (mm/dd/yyyy)");
        
        date = userInput.nextLine();
        dateArr = date.split("/");
        userOrigBDay = LocalDate.parse(date, formatter);
       
        date = dateArr[0] + "/" + dateArr[1] + "/" + Year.now().getValue();
        userBDayNext = LocalDate.parse(date, formatter);
        
        System.out.println("\nThat means you were born on a " + userOrigBDay.getDayOfWeek() + "!");
        System.out.println("This year it falls on a " + userBDayNext.getDayOfWeek() + "...");
        
        userBDayNext = userBDayNext.plusYears(1);
        long daysDiff = ChronoUnit.DAYS.between(dayToday, userBDayNext);
        past = userBDayNext.minusYears(userOrigBDay.getYear());
        
        System.out.println("And since today is " + currentDay + ", there's only " + daysDiff + " days until the next one!");
        System.out.println("Bet yer excited to be turning " + past.getYear() + "!");
    }
}