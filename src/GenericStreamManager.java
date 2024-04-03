package src;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class GenericStreamManager extends AbstractStreamManager{
    protected InputStream inStream;
    protected PrintStream outStream;
    private Scanner input;
    public GenericStreamManager(InputStream in, PrintStream out) {
        inStream = in;
        outStream = out;
        input = new Scanner(inStream);
    }

    @Override
    public String read() {
        try {
            return input.next();
        }
        catch (Exception e) {
            return "";
        }
    }

    @Override
    public void print(String s) {
        outStream.print(s);
    }

    @Override
    public void println(String s) {
        outStream.println(s);
    }

    @Override
    public void close() throws IOException {
        inStream.close();
        outStream.close();
    }
}
