/**
* RockPaperScissors - This class is for the game rock, paper, and scissors.
* The game is played between the user and the computer (bot) to determine who
* won the round(s) and game(s). They can play an infinite amoutn of games but
* can play no more than 10 rounds per game.
* -----------------------------------------------
* @author Matthew Shoemate
*        Created: 07/10/2020
*/

package mshoe.assessmentone;

import java.util.HashMap;
import java.util.Map;
import java.util.Random; //import the Random class
import java.util.Scanner; //import the Scanner class
import java.util.Set;

public class RockPaperScissors
{
    //main - where the user enters the game
    public static void main(String[] args)
    {
        boolean didGameStart = true, goodInput; //declare as boolean and initialize them
        int numRounds = 0, versionGame = 0; //declare them as int and initialize them to 0
        String userChoice = "n"; //declare as String and initialize it to "n"
        Scanner userInput = new Scanner(System.in);
        
        while(didGameStart) //while loop for if the game started (true) or if it did not (false) due to bad input
        {
            goodInput = false; //initialize goodInput to false

            while(!goodInput) //while goodInput is false - keep in the loop until the user enters a whole number
            {
                System.out.println("What version of Rock, Paper, Scissors game would you like to play? (Please choose 1, 2 or 3)\n1) Classic\n2) Big Bang Theory\n3) Extended");
                
                try
                {
                    versionGame = Integer.parseInt(userInput.nextLine());
                    goodInput = CheckVersionGame(versionGame); //variable goodInput = the return value of CheckVersionGame
                } catch(NumberFormatException e)
                {
                    System.out.println("\nYou did not input a whole number.");
                    continue;
                }
                
                System.out.println("\nHow many rounds would you like to play?");
                
                try //try catch to determine whether the user put in a whole number integer - if they did not then catch the error and print out a message
                {
                    numRounds = Integer.parseInt(userInput.nextLine());
                } catch(NumberFormatException e)
                {
                    System.out.println("\nYou did not input a whole number.");
                    goodInput = false;
                    break; //break the while loop
                }
            }
            
            if(!goodInput)
            {
                break; //break the while loop to quit the program
            }
            
            goodInput = false; //re-initialize goodInput to false

            didGameStart = StartGame(numRounds, versionGame); //this will come back with true to stay in the while loop or false to exit the while loop
            
            while(!goodInput) //while goodInput is false - keep in the loop until the user enters a good y/n
            {
                System.out.println("\nWould you like to play again? (y/n)");
                userChoice = userInput.nextLine();
                System.out.println("");
                
                if(!userChoice.equals("n") && !userChoice.equals("y"))
                {
                    System.out.println("\nThe input must be a 'y' or 'n'.");
                } else
                {
                    goodInput = true;
                }
            }
            
            if(userChoice.equals("n"))
            {
                System.out.println("\n\nThank you for playing Rock, Paper, Scissors!");
                break; //exit the while loop
            }
        }
    }
    
    /** CheckRounds - a public static boolean method that checks whether the number of rounds inputted
    * from the user is good, or not. If it is less than one or more than 10 then the number of rounds is invalid.
    * ----------------------------------------------------------------------------------------------------------
    * @param numRounds - int
    * @return isNumRoundsGood
    */
    public static boolean CheckRounds(int numRounds)
    {
        boolean isNumRoundsGood = true; //declare as a boolean and initialize it to true
        
        if(numRounds > 10) //if the number of rounds is 11 to infinity - exit
        {
            System.out.println("\nInvalid maximum amount of rounds! Must be maximum of 10 rounds.");
            isNumRoundsGood = false;
        } else if(numRounds < 1) //if the number of rounds is less than 1 - exit
        {
            System.out.println("\nInvalid minimum amount of rounds! Must be at least 1 round.");
            isNumRoundsGood = false;
        }
        
        return isNumRoundsGood; //returns if the number of rounds input from the user is good (true) or not (false)
    }
    
    /** CheckVersionGame - a public static boolean method that checks whether the number of version game inputted
    * from the user is good, or not. If it is less than one or more than 3, then the version game is invalid.
    * ----------------------------------------------------------------------------------------------------------
    * @param versionGame - int
    * @return isVersionGameGood
    */
    public static boolean CheckVersionGame(int versionGame)
    {
        boolean isVersionGameGood = true; //declare as a boolean and initialize it to true
        
        if(versionGame < 1 || versionGame > 3) //if the version of the game is either less than 1 or greater than 3 - get different input
        {
            System.out.println("\nInvalid version of the game! Must be 1, 2, or 3.");
            isVersionGameGood = false;
        }
        
        return isVersionGameGood; //returns if the version of the game from the user is 1, 2, or 3 (true) or not (false)
    }
    
    /** CheckUserGame - a public static boolean method that checks whether the number of the user's choice inputted
     * is good or not. It checks the version game. So, if it was the classic version, their choice is between 1 and 3.
     * Big Bang Theory version is between 1 and 5 and the extended version is between 1 and 7.
     * ----------------------------------------------------------------------------------------------------------
     * @param versionGame
     * @return isUserChoiceGood
     */
    public static int CheckUserChoice(int versionGame)
    {
        boolean goodInput = false;
        int userChoice = 0;
        Scanner userInput = new Scanner(System.in);
        
        while(!goodInput)
        {
            if(versionGame == 1)
            {
                System.out.println("\nWhat is your choice? Please type in the number associated with each choice:\n1) Rock\n2) Paper\n3) Scissors");
            } else if(versionGame == 2)
            {
                System.out.println("\nWhat is your choice? Please type in the number associated with each choice:\n1) Rock\n2) Paper\n3) Scissors\n4) Spock\n5) Lizard");
            } else
            {
                System.out.println("\nWhat is your choice? Please type in the number associated with each choice:\n1) Rock\n2) Paper\n3) Scissors\n4) Water\n5) Air\n6) Sponge\n7) Fire");
            }
            
            try
            {
                userChoice = Integer.parseInt(userInput.nextLine());
            } catch(NumberFormatException e)
            {
                System.out.println("\n" + userChoice + " is not a whole number. Please put in a whole number.");
                continue;
            }
            
            if(userChoice >= 1 && userChoice <= 7)
            {
                if((versionGame == 1 && userChoice > 3) || (versionGame == 2 && userChoice > 5))
                {
                    System.out.println("\nYour choice must be one of the numbers given.");
                } else
                {
                    goodInput = true;
                }
            } else
            {
                System.out.println("\nYour choice must be one of the numbers given.");
            }
        }
        
        return userChoice;
    }
    
    /** StartGame - a public static boolean method that asks the user their choice for rock, paper, or scissors.
    * It will then print out the round decision and the game's decision once the number of rounds are up.
    * ----------------------------------------------------------------------------------------------------------
    * @param numRounds - int
    * @param versionGame - int
    * @return startGame
    */
    public static boolean StartGame(int numRounds, int versionGame)
    {
        boolean startGame; //declare as a boolean
        int countRounds = 1, userWins = 0, userLosses = 0, roundTies = 0; //declare as an int and initialize them
        Map<String, Integer> roundResults = new HashMap<>();
        
        roundResults.put("Wins", 0);
        roundResults.put("Losses", 0);
        roundResults.put("Ties", 0);
        
        startGame = CheckRounds(numRounds); //this will come back with true to start the game or false to completely end the game
        
        if(startGame) //if startGame = true, go into the conditional; if startGame = false, exit
        {
            while(countRounds <= numRounds) //while countRounds (incremented) is less than or equal to numRounds (user input of number of rounds)
            {
                switch(versionGame)
                {
                    case 1:
                        roundResults = ClassicVersion(versionGame, roundResults); //go to method ClassicVersion and pass the variable, versionGame; this starts the classic version
                        break;
                    case 2:
                        roundResults = BBTVersion(versionGame, roundResults); //go to method BBTVersion and pass the variable, versionGame; this starts the Big Bang Theory version
                        break;
                    case 3:
                        roundResults = ExtendedVersion(versionGame, roundResults); //go to method ExtendedVersion and pass the variable, versionGame; this starts the extended version
                        break;
                    default:
                        break;
                }

                userWins = roundResults.get("Wins");
                userLosses = roundResults.get("Losses");
                roundTies = roundResults.get("Ties");
                
                System.out.println("\nRound " + countRounds + " out of " + numRounds); //prints out the current round out of total rounds
                //prints out user statistics of the game
                System.out.println("User Statistics\n-----------------------\nWins: " + userWins + " Losses: " + userLosses + " Ties: " + roundTies);
                countRounds++; //increment countRounds
            }
            
            if(userWins == userLosses)
            {
                System.out.println("\nNo one won! You tied with the bot with " + userWins + " wins, " + userLosses + " losses, and " + roundTies + " ties!");
            } else if(userWins > userLosses)
            {
                System.out.println("\nYou won! You won versus the bot with " + userWins + " wins, " + userLosses + " losses, and " + roundTies + " ties!");
            } else
            {
                System.out.println("\nYou lost! You lost versus the bot with " + userWins + " wins, " + userLosses + " losses, and " + roundTies + " ties!");
            }
        }
        
        return startGame; //returns the boolean value of true or false if the game started (true) or not (false) - if it did not start, exit the game/class
    }
    
    /** RoundDecision - a public static void method that decides if the user has lossed, won, or tied
    * with the bot. It returns a 0, 1, or 2 whether if the round was a loss, win, or a tie.
    * ----------------------------------------------------------------------------------------------
    * @param botChoice - int
    * @param userChoice - int
    * @param versionGame - int
    * @param roundResults - Map<String, Integer>
    * @return roundResults
    */
    public static Map<String, Integer> RoundDecision(int botChoice, int userChoice, int versionGame, Map<String, Integer> roundResults)
    {
        int roundDecision = 0; //declare as int and initialize to 0 which is a tie in the roundDecision - does not get rewritten if it is a tie
        int userWins = roundResults.get("Wins");
        int userLosses = roundResults.get("Losses");
        int roundTies = roundResults.get("Ties");
        String botChoiceSpelled = "";
        
        Set<String> keys = roundResults.keySet(); //get the set of keys from the map
       
        for(String k : keys) //the keys and associated values
        {
            if(roundResults.get(k) == -1)
            {
                botChoiceSpelled = k;
                roundResults.remove(k);
                break;
            }
        }
        System.out.println("here");
        if(userChoice != botChoice)
        {
            switch(botChoice)
            {
                //basic switch-case that has multi if-then-else conditionals inside each case; determines the round decision (tie (0), win (1), or loss (2))
                case 1:
                    if(userChoice == 2) //if userChoice = 2 (paper), user wins (bot has 1 - rock)
                    {
                        roundDecision = 1;
                    } else if(userChoice == 3) //if userChoice = 3 (scissors), user loses (bot has 1 - rock)
                    {
                        roundDecision = 2;
                    }

                    break;
                case 2:
                    if(userChoice == 1) //if userChoice = 1 (rock), user loses (bot has 2 - paper)
                    {
                        roundDecision = 2;
                    } else if(userChoice == 3) //if userChoice = 3 (scissors), user wins (bot has 2 - paper)
                    {
                        roundDecision = 1;
                    }

                    break;
                case 3:
                    if(userChoice == 1) //if userChoice = 1 (rock), user wins (bot has 3 - scissors)
                    {
                        roundDecision = 1;
                    } else if(userChoice == 2) //if userChoice = 2 (paper), user loses (bot has 3 - scissors)
                    {
                        roundDecision = 2;
                    }

                    break;
                default:
                    break;
            }

            if(roundDecision == 0)
            {
                if(versionGame == 2)
                {
                    //basic switch-case that has multi if-then-else conditionals inside each case; determines the round decision (tie (0), win (1), or loss (2))
                    switch(botChoice)
                    {
                        case 1:
                            if(userChoice == 4) //if userChoice = 4 (spock), user wins (bot has 1 - rock)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 5) //if userChoice = 5 (lizard), user loses (bot has 1 - rock)
                            {
                                roundDecision = 2;
                            }

                            break;
                        case 2:
                            if(userChoice == 4) //if userChoice = 4 (spock), user loses (bot has 2 - paper)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 5) //if userChoice = 5 (lizard), user loses (bot has 2 - paper)
                            {
                                roundDecision = 1;
                            }

                            break;
                        case 3:
                            if(userChoice == 4) //if userChoice = 4 (spock), user wins (bot has 3 - scissors)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 5) //if userChoice = 5 (lizard), user loses (bot has 3 - scissors)
                            {
                                roundDecision = 2;
                            }

                            break;
                        case 4:
                            if(userChoice == 1) //if userChoice = 1 (rock), user loses (bot has 4 - spock)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 2) //if userChoice = 2 (paper), user wins (bot has 4 - spock)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 3) //if userChoice = 3 (scissors), user loses (bot has 4 - spock)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 5) //if userChoice = 5 (lizard), user wins (bot has 4 - spock)
                            {
                                roundDecision = 1;
                            }

                            break;
                        case 5:
                            if(userChoice == 1) //if userChoice = 1 (rock), user wins (bot has 5 - lizard)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 2) //if userChoice = 2 (paper), user loses (bot has 5 - lizard)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 3) //if userChoice = 3 (scissors), user wins (bot has 5 - lizard)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 4) //if userChoice = 4 (spock), user loses (bot has 5 - lizard)
                            {
                                roundDecision = 2;
                            }

                            break;
                        default:
                            break;
                    }
                } else
                {
                    //basic switch-case that has multi if-then-else conditionals inside each case; determines the round decision (tie (0), win (1), or loss (2))
                    switch(botChoice)
                    {
                        case 1:
                            if(userChoice == 4) //if userChoice = 4 (water), user wins (bot has 1 - rock)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 5) //if userChoice = 5 (air), user wins (bot has 1 - rock)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 6) //if userChoice = 6 (sponge), user loses (bot has 1 - rock)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 7) //if userChoice = 7 (fire), user loses (bot has 1 - rock)
                            {
                                roundDecision = 2;
                            }

                            break;
                        case 2:
                            if(userChoice == 4) //if userChoice = 4 (water), user loses (bot has 2 - paper)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 5) //if userChoice = 5 (air), user loses (bot has 2 - paper)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 6) //if userChoice = 6 (sponge), user wins (bot has 2 - paper)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 7) //if userChoice = 7 (fire), user wins (bot has 2 - paper)
                            {
                                roundDecision = 1;
                            }

                            break;
                        case 3:
                            if(userChoice == 4) //if userChoice = 4 (water), user wins (bot has 3 - rock)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 5) //if userChoice = 5 (air), user wins (bot has 3 - rock)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 6) //if userChoice = 6 (sponge), user loses (bot has 3 - rock)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 7) //if userChoice = 7 (fire), user loses (bot has 3 - rock)
                            {
                                roundDecision = 2;
                            }

                            break;
                        case 4:
                            if(userChoice == 1) //if userChoice = 1 (rock), user loses (bot has 4 - water)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 2) //if userChoice = 2 (paper), user wins (bot has 4 - water)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 3) //if userChoice = 3 (scissors), user loses (bot has 4 - water)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 5) //if userChoice = 5 (air), user wins (bot has 4 - water)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 6) //if userChoice = 6 (sponge), user wins (bot has 4 - water)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 7) //if userChoice = 7 (fire), user loses (bot has 4 - water)
                            {
                                roundDecision = 2;
                            }

                            break;
                        case 5:
                            if(userChoice == 1) //if userChoice = 1 (rock), user loses (bot has 5 - air)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 2) //if userChoice = 2 (paper), user wins (bot has 5 - air)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 3) //if userChoice = 3 (scissors), user wins (bot has 5 - air)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 4) //if userChoice = 4 (water), user loses (bot has 5 - air)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 6) //if userChoice = 6 (sponge), user wins (bot has 5 - air)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 7) //if userChoice = 7 (fire), user loses (bot has 5 - air)
                            {
                                roundDecision = 2;
                            }

                            break;
                        case 6:
                            if(userChoice == 1) //if userChoice = 1 (rock), user wins (bot has 6 - sponge)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 2) //if userChoice = 2 (paper), user loses (bot has 6 - sponge)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 3) //if userChoice = 3 (scissors), user wins (bot has 6 - sponge)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 4) //if userChoice = 4 (water), user loses (bot has 6 - sponge)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 5) //if userChoice = 5 (air), user loses (bot has 6 - sponge)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 7) //if userChoice = 7 (fire), user wins (bot has 6 - sponge)
                            {
                                roundDecision = 1;
                            }

                            break;
                        case 7:
                            if(userChoice == 1) //if userChoice = 1 (rock), user wins (bot has 7 - fire)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 2) //if userChoice = 2 (paper), user loses (bot has 7 - fire)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 3) //if userChoice = 3 (scissors), user loses (bot has 7 - fire)
                            {
                                roundDecision = 2;
                            } else if(userChoice == 4) //if userChoice = 4 (water), user wins (bot has 7 - fire)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 5) //if userChoice = 5 (air), user wins (bot has 7 - fire)
                            {
                                roundDecision = 1;
                            } else if(userChoice == 6) //if userChoice = 6 (sponge), user loses (bot has 7 - fire)
                            {
                                roundDecision = 2;
                            }

                            break;
                        default:
                            break;
                    }
                }
            }
        }
        
        switch(roundDecision) //basic switch-case for the variable roundDecision - 0, 1, or 2; prints out and increments the decision
        {
            case 0:
                System.out.println("\nThe bot's choice was " + botChoiceSpelled + "! You tied!");
                roundTies++; //increment roundTies
                break;
            case 1:
                System.out.println("\nThe bot's choice was " + botChoiceSpelled + "! You win!");
                userWins++; //increment userWins
                break;
            case 2:
                System.out.println("\nThe bot's choice was " + botChoiceSpelled + "! You lose!");
                userLosses++; //increment userLosses
                break;
            default:
                break;
        }
        
        roundResults.put("Wins", userWins);
        roundResults.put("Losses", userLosses);
        roundResults.put("Ties", roundTies);
        
        return roundResults;
    }
    
    /** ClassicVersion - a public static void method, it starts the classic version of the game. It asks the user the
    * specified choice for the version of the game.
    * ----------------------------------------------------------------------------------------------------------
    * @param versionGame - int
    * @param roundResults - Map<String, Integer>
    * @return roundResults
    */
    public static Map<String, Integer> ClassicVersion(int versionGame, Map<String, Integer> roundResults)
    {
        boolean goodInput = false;
        int botChoice = 0, userChoice = 0;
        String botChoiceSpelled = "";
        Random botPlayer = new Random();
        
        userChoice = CheckUserChoice(versionGame);

        botChoice = botPlayer.nextInt(3 - 1 + 1) + 1; //nextInt(max - min + 1) + min - get the range and choose between the random numbers of 1 - 3

        switch(botChoice) //basic switch-case for the variable botChoice - botChoiceSpelled is then assigned to what choice the bot used
        {
            case 1:
                botChoiceSpelled = botChoice + " - Rock"; //ex: 1 - Rock
                break;
            case 2:
                botChoiceSpelled = botChoice + " - Paper"; //ex: 2 - Paper
                break;
            case 3:
                botChoiceSpelled = botChoice + " - Scissors"; //ex: 3 - Scissors
                break;
            default:
                break;
        }
        
        roundResults.put(botChoiceSpelled, -1);
        
        //this will come back with a 0, 1, or 2 to determine who tied (0), won (1), or lossed (2)
        roundResults = RoundDecision(botChoice, userChoice, versionGame, roundResults);
        
        return roundResults;
    }
    
    /** BBTVersion - a public static void method, it starts the Big Bang Theory version of the game. It asks the user
    * the specified choice for the version of the game.
    * ----------------------------------------------------------------------------------------------------------
    * @param versionGame - int
    * @param roundResults - Map<String, Integer>
    * @return roundResults
    */
    public static Map<String, Integer> BBTVersion(int versionGame, Map<String, Integer> roundResults)
    {
        int botChoice = 0, userChoice = 0;
        String botChoiceSpelled = "";
        Random botPlayer = new Random();
        
        userChoice = CheckUserChoice(versionGame);
        
        botChoice = botPlayer.nextInt(5 - 1 + 1) + 1; //nextInt(max - min + 1) + min - get the range and choose between the random numbers of 1 - 3

        switch(botChoice) //basic switch-case for the variable botChoice - botChoiceSpelled is then assigned to what choice the bot used
        {
            case 1:
                botChoiceSpelled = botChoice + " - Rock"; //ex: 1 - Rock
                break;
            case 2:
                botChoiceSpelled = botChoice + " - Paper"; //ex: 2 - Paper
                break;
            case 3:
                botChoiceSpelled = botChoice + " - Scissors"; //ex: 3 - Scissors
                break;
            case 4:
                botChoiceSpelled = botChoice + " - Spock"; //ex: 4 - Spock
                break;
            case 5:
                botChoiceSpelled = botChoice + " - Lizard"; //ex: 5 - Lizard
                break;
            default:
                break;
        }

        roundResults.put(botChoiceSpelled, -1);
        
        //this will come back with a 0, 1, or 2 to determine who tied (0), won (1), or lossed (2)
        roundResults = RoundDecision(botChoice, userChoice, versionGame, roundResults);
        
        return roundResults;
    }
    
    /** ExtendedVersion - a public static void method, it starts the extended version of the game. It asks the user
    * the specified choice for the version of the game.
    * ----------------------------------------------------------------------------------------------------------
    * @param versionGame - int
    * @param roundResults - Map<String, Integer>
    * @return roundResults
    */
    public static Map<String, Integer> ExtendedVersion(int versionGame, Map<String, Integer> roundResults)
    {
        int botChoice = 0, userChoice = 0;
        String botChoiceSpelled = "";
        Random botPlayer = new Random();
        
        userChoice = CheckUserChoice(versionGame);

        botChoice = botPlayer.nextInt(7 - 1 + 1) + 1; //nextInt(max - min + 1) + min - get the range and choose between the random numbers of 1 - 3

        switch(botChoice) //basic switch-case for the variable botChoice - botChoiceSpelled is then assigned to what choice the bot used
        {
            case 1:
                botChoiceSpelled = botChoice + " - Rock"; //ex: 1 - Rock
                break;
            case 2:
                botChoiceSpelled = botChoice + " - Paper"; //ex: 2 - Paper
                break;
            case 3:
                botChoiceSpelled = botChoice + " - Scissors"; //ex: 3 - Scissors
                break;
            case 4:
                botChoiceSpelled = botChoice + " - Water"; //ex: 4 - Water
                break;
            case 5:
                botChoiceSpelled = botChoice + " - Air"; //ex: 5 - Air
                break;
            case 6:
                botChoiceSpelled = botChoice + " - Sponge"; //ex: 6 - Sponge
                break;
            case 7:
                botChoiceSpelled = botChoice + " - Fire"; //ex: 7 - Fire
                break;
            default:
                break;
        }
        
        roundResults.put(botChoiceSpelled, -1);

        //this will come back with a 0, 1, or 2 to determine who tied (0), won (1), or lossed (2)
        roundResults = RoundDecision(botChoice, userChoice, versionGame, roundResults);
        
        return roundResults;
    }
}