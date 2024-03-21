import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.File;


public class TestGuessingGame extends SampleGuessingGame
{
    public static void test(int n)
    {
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
            runGame(inStream, outStream);
        }
        
        
        
        
        
    }
}