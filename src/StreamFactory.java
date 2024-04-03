package src;

import java.io.InputStream;
import java.io.PrintStream;

public class StreamFactory {
    public static enum StreamType {
        STANDARD,
        COMPUTER,
        CUSTOM;
    }
    private StreamFactory(){}
    public static AbstractStreamManager getStreamManager(StreamType inType, StreamType outType) throws IllegalArgumentException
    {
        if (inType != outType) {
            throw new IllegalArgumentException("Must have same in and out type");
        }
        else {
            if (inType == StreamType.STANDARD) {
                return new GenericStreamManager(System.in, System.out);
            }
            else if (inType == StreamType.COMPUTER) {
                return new ComputerStreamManager();
            }
            else {
                return new CustomStreamManager();
            }
        }
    }
    public static AbstractStreamManager getStreamManager(InputStream inStream, StreamType outType) throws IllegalArgumentException
    {
        if (outType == StreamType.CUSTOM) {
            throw new IllegalArgumentException("Cannot use Custom Stream type for only one direction");
        }
        return new GenericStreamManager(inStream, System.out);
    }
    public static AbstractStreamManager getStreamManager(StreamType inType, PrintStream outStream)
    {
        if (inType == StreamType.CUSTOM) {
            throw new IllegalArgumentException("Cannot use Custom Stream type for only one direction");
        }
        return new GenericStreamManager(System.in, outStream);
    }
    public static AbstractStreamManager getStreamManager(InputStream inStream, PrintStream outStream)
    {
        return new GenericStreamManager(inStream, outStream);
    }
}
