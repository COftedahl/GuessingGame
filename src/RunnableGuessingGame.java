package src;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class RunnableGuessingGame implements Runnable
{
    protected static final int ALLOWED_GUESSES = 7;
    protected static final int BOTTOM = 1;
    protected static final int TOP = 100;
    protected static final int RANGE = TOP - BOTTOM + 1;
    protected boolean askingToPlayAgain = false;
    protected boolean wonGame = false;
    protected AbstractStreamManager streamMan = null;
    protected GameResultsImpl results = new GameResultsImpl();
    public RunnableGuessingGame() {
        streamMan = StreamFactory.getStreamManager(StreamFactory.StreamType.STANDARD, StreamFactory.StreamType.STANDARD);
    }
    public RunnableGuessingGame(AbstractStreamManager streamManager) {
        streamMan = streamManager;
    }
    private void runGame()
    {
        boolean continuePlaying = true;
        boolean succeeded = false;
        
        int numGuessesUsed = 0;
        int numToGuess = -1;
        boolean validInput = true;
        //Scanner input = new Scanner(in);
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
            streamMan.println("Welcome to the Guessing Game! You must guess a randomly selected " +
                "whole number between " + BOTTOM + " and " + TOP + " in " + ALLOWED_GUESSES + " guesses! Good Luck!");
            while ((succeeded == false) && (numGuessesUsed < ALLOWED_GUESSES))
            {
                streamMan.print("You have " + (ALLOWED_GUESSES - numGuessesUsed) + " guesses remaining. ");
                if (numGuessesUsed > 0)
                {
                    streamMan.println("Here are your guesses: ");
                    for (int i = 0; i < numGuessesUsed; i += 1)
                    {
                        streamMan.println("Guess " + (i + 1) + ": " + guesses[i]);
                    }
                }
                streamMan.print("Enter a number between " + BOTTOM + " and " + TOP + ". \n>>  ");
                temp = streamMan.read();
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
                    streamMan.println("You did not enter a valid number. " +
                        "Enter a number between " + BOTTOM + " and " + TOP + ". \n>>  ");
                    temp = streamMan.read();
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
                GameMoves move = new GameMoves(currGuess, GameResultsImpl.Result.CORRECT);
                if (currGuess > numToGuess)
                {
                    streamMan.println("Wrong - Your guess, " + currGuess + ", was too high. ");
                    move.setResult(GameResultsImpl.Result.LOWER);
                }
                else if (currGuess < numToGuess)
                {
                    streamMan.println("Wrong - Your guess, " + currGuess + ", was too low. ");
                    move.setResult(GameResultsImpl.Result.HIGHER);
                }
                else
                {
                    succeeded = true;
                    streamMan.println("Yes! You guessed it! The number was " + currGuess + ". " +
                        "\nHere are your guesses: ");
                    for (int i = 0; i < numGuessesUsed; i += 1)
                    {
                        streamMan.println("Guess " + (i + 1) + ": " + guesses[i]);
                    }
                }
                if ((numGuessesUsed == ALLOWED_GUESSES) && (currGuess != numToGuess))
                {
                    streamMan.println("The answer was " + numToGuess + ". ");
                }
                results.moves.add(move);
                
            }
            wonGame = succeeded;
            streamMan.print("Do you want to play again? \n>>  ");
            results.numMovesUsed = numGuessesUsed;
            results.wonGame = wonGame;

            askingToPlayAgain = true;
            temp = streamMan.read();
            pattern = Pattern.compile("y(es)?.?", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(temp);
            askingToPlayAgain = false;
            wonGame = false;
            if (matcher.find())
            {
                continuePlaying = true;
            }
            else
            {
                continuePlaying = false;
                try {
                    streamMan.close();
                    System.out.println("Closing");
                }
                catch (Exception e) {
                    streamMan.print("failed to close streams");
                }
                System.exit(0);
            }
        }
    }

    public GameResultsImpl getResults() {
        return results;
    }
    public void run() {
        runGame();
    }
}
