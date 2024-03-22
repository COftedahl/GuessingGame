import javax.swing.*;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.function.Predicate;

public class CustomStreamManager extends AbstractStreamManager
{
    //Class to implementing Javax.swing UI in place of Streams
    protected String buffer;
    protected int maxBufferLength;
    private static final int MAXLENGTH_SHORT = 50;
    private static final int MAXLENGTH_MEDIUM = 100;
    private static final int MAXLENGTH_LONG = 200;
    private static final int MAXLENGTH_XLONG = 300;
    public enum bufferLength {
        SHORT,
        MEDIUM,
        LONG,
        EXTRA_LONG;
    }
    public CustomStreamManager() {
        buffer = "";
        maxBufferLength = MAXLENGTH_SHORT;
    }
    public CustomStreamManager(bufferLength l) {
        buffer = "";
        switch (l) {
            case SHORT:
                maxBufferLength = MAXLENGTH_SHORT;
                break;
            case MEDIUM:
                maxBufferLength = MAXLENGTH_MEDIUM;
                break;
            case LONG:
                maxBufferLength = MAXLENGTH_LONG;
                break;
            case EXTRA_LONG:
                maxBufferLength = MAXLENGTH_XLONG;
                break;
            default:
                maxBufferLength = MAXLENGTH_SHORT;
                break;
        }
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public void print(String s) {

    }

    @Override
    public void println(String s) {
        
    }
    public static void main(String[] args) {
        String temp = JOptionPane.showInputDialog(null, "Enter your age", "Age Input Screen", JOptionPane.QUESTION_MESSAGE);
        JOptionPane.showMessageDialog(null, temp, "Age Output", JOptionPane.INFORMATION_MESSAGE);
    }
}