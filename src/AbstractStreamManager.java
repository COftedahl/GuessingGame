package src;

import java.io.Closeable;
import java.io.IOException;

public abstract class AbstractStreamManager implements Closeable {
    public abstract String  read();
    public abstract void print(String s);
    public abstract void println(String s);
}
