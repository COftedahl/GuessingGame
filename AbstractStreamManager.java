import java.io.IOException;

public abstract class AbstractStreamManager {
    public abstract int  read() throws IOException;
    public abstract void print(String s);
    public abstract void println(String s);
}
