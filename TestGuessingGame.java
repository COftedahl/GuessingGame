import src.SampleGuessingGameReworked;
import src.StreamFactory;

import java.io.*;


public class TestGuessingGame extends SampleGuessingGameReworked
{
    public static void mains(String[] args) {
        SampleGuessingGameReworked game = new SampleGuessingGameReworked();
        game.runGame(StreamFactory.getStreamManager(StreamFactory.StreamType.CUSTOM, StreamFactory.StreamType.CUSTOM));
    }
    public static void main(String[] args) {
        SampleGuessingGameReworked game = new SampleGuessingGameReworked();
        game.runGame(StreamFactory.getStreamManager(StreamFactory.StreamType.STANDARD, StreamFactory.StreamType.STANDARD));
    }
    public static void test(int n)
    {
        SampleGuessingGameReworked game = new SampleGuessingGameReworked();
        String input = "";
        String outputFilePath = "C:\\Users\\coley\\OneDrive\\Desktop\\testGuessingGameOutput.txt";
        int temp = (int)((Math.random() * RANGE) + BOTTOM);
        for (int i = 0; i < n - 1; i += 1)
        {
            for (int j = 0; j < ALLOWED_GUESSES; j += 1)
            {
                input += temp + " ";
                temp = (int)((Math.random() * RANGE) + BOTTOM);
            }
            input += "YES ";
        }
        for (int j = 0; j < ALLOWED_GUESSES; j += 1)
        {
            input += temp + " ";
            temp = (int)((Math.random() * RANGE) + BOTTOM);
        }
        InputStream inStream;
        try
        {
            inStream = new ByteArrayInputStream(input.getBytes());
        }
        catch (Exception e)
        {
            inStream = null;
        }
        
        File outputFile = new File(outputFilePath);
        PrintStream outStream;
        try
        {
            outStream = new PrintStream(outputFile);
        }
        catch (Exception e)
        {
            outStream = null;
        }
        if ((inStream == null) || (outStream == null))
        {
            System.out.println("Failed to initialize streams");
        }
        else
        {
            game.runGame(StreamFactory.getStreamManager(StreamFactory.StreamType.CUSTOM, StreamFactory.StreamType.CUSTOM));
        }
    }
}