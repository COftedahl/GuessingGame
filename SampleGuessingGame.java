import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.InputStream;
import java.io.PrintStream;

public class SampleGuessingGame
{
    protected static final int ALLOWED_GUESSES = 7;
    protected static final int BOTTOM = 1;
    protected static final int TOP = 100;
    protected static final int RANGE = TOP - BOTTOM + 1;
    public static void main(String[] args)
    {
        runGame();
    }
    public static void runGame()
    {
        runGame(System.in, System.out);
    }
    public static void runGame(InputStream in, PrintStream out)
    {
        boolean continuePlaying = true;
        boolean succeeded = false;
        
        int numGuessesUsed = 0;
        int numToGuess = -1;
        boolean validInput = true;
        Scanner input = new Scanner(in);
        String temp;
        int currGuess = -1;
        Pattern pattern;
        Matcher matcher;
        int[] guesses;
        while (continuePlaying)
        {
            numGuessesUsed = 0;
            succeeded = false;
            guesses = new int[ALLOWED_GUESSES];
            numToGuess = (int)((Math.random() * RANGE) + BOTTOM);
            out.println("Welcome to the Guessing Game! You must guess a randomly selected " + 
                "whole number between " + BOTTOM + " and " + TOP + " in " + ALLOWED_GUESSES + " guesses! Good Luck!");
            while ((succeeded == false) && (numGuessesUsed < ALLOWED_GUESSES))
            {
                out.print("You have " + (ALLOWED_GUESSES - numGuessesUsed) + " guesses remaining. ");
                if (numGuessesUsed > 0)
                {
                    out.println("Here are your guesses: ");
                    for (int i = 0; i < numGuessesUsed; i += 1)
                    {
                        out.println("Guess " + (i + 1) + ": " + guesses[i]);
                    }
                }
                out.print("Enter a number between " + BOTTOM + " and " + TOP + ". \n>>  ");
                temp = input.next();
                try
                {
                    currGuess = Integer.parseInt(temp);
                    validInput = true;
                }
                catch (Exception e)
                {
                    validInput = false;
                }
                while (validInput == false)
                {
                    out.println("You did not enter a valid number. " +
                        "Enter a number between " + BOTTOM + " and " + TOP + ". \n>>  ");
                    temp = input.next();
                    try
                    {
                        currGuess = Integer.parseInt(temp);
                        validInput = true;
                    }
                    catch (Exception e)
                    {
                        validInput = false;
                    }
                }
                guesses[numGuessesUsed] = currGuess;
                numGuessesUsed += 1;
                if (currGuess > numToGuess)
                {
                    out.println("Wrong - Your guess, " + currGuess + ", was too high. ");
                }
                else if (currGuess < numToGuess)
                {
                    out.println("Wrong - Your guess, " + currGuess + ", was too low. ");
                }
                else
                {
                    succeeded = true;
                    out.println("Yes! You guessed it! The number was " + currGuess + ". " + 
                        "\nHere are your guesses: ");
                    for (int i = 0; i < numGuessesUsed; i += 1)
                    {
                        out.println("Guess " + (i + 1) + ": " + guesses[i]);
                    }
                }
                if ((numGuessesUsed == ALLOWED_GUESSES) && (currGuess != numToGuess))
                {
                    out.println("The answer was " + numToGuess + ". ");
                }
                
            }
            out.print("Do you want to play again? \n>>  ");
            temp = input.next();
            pattern = Pattern.compile("y(es)?.?", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(temp);
            if (matcher.find())
            {
                continuePlaying = true;
            }
            else
            {
                continuePlaying = false;
            }
        }
    }
}
