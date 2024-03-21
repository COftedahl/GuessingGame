import java.io.PrintStream;
import javax.swing.*;

public class CustomOutStream extends PrintStream
{
    private String buffer;
    CustomOutStream(String s) throws Exception
    {
        super(s);
        buffer = "";
    }
    
}