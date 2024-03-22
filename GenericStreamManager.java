import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class GenericStreamManager extends AbstractStreamManager{
    InputStream inStream;
    PrintStream outStream;
    public GenericStreamManager(InputStream in, PrintStream out) {
        inStream = in;
        outStream = out;
    }

    @Override
    public int read() throws IOException {
        return inStream.read();
    }

    @Override
    public void print(String s) {
        outStream.print(s);
    }

    @Override
    public void println(String s) {
        outStream.println(s);
    }
}
