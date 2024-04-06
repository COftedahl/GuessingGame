package src;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomStreamManager extends AbstractStreamManager
{
    //Class to implementing Javax.swing UI in place of Streams
    protected String buffer;
    protected int maxBufferLength;
    protected boolean bufferChanged = false;
    protected boolean requestedRead = false;
    private ExecutorService pool;
    private Lock preventMultipleTasksLock;
    private Lock lock;
    private static final int MAXLENGTH_SHORT = 50;
    private static final int MAXLENGTH_MEDIUM = 100;
    private static final int MAXLENGTH_LONG = 200;
    private static final int MAXLENGTH_XLONG = 300;
    private static final int MAX_T = 1;

    public enum bufferLength {
        SHORT,
        MEDIUM,
        LONG,
        EXTRA_LONG;
    }
    public CustomStreamManager() {
        buffer = "";
        maxBufferLength = MAXLENGTH_SHORT;
        pool = Executors.newFixedThreadPool(MAX_T);
        preventMultipleTasksLock = new ReentrantLock();
        lock = new ReentrantLock();
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
        pool = Executors.newFixedThreadPool(MAX_T);
        preventMultipleTasksLock = new ReentrantLock();
        lock = new ReentrantLock();
    }

    @Override
    public String read() {
        lock.lock();
        requestedRead = true;
        lock.unlock();
        preventMultipleTasksLock.lock();
        lock.lock();
        String temp = buffer;
        buffer = "";
        bufferChanged = false;
        requestedRead = false;
        lock.unlock();
        return JOptionPane.showInputDialog(null, temp, "Input", JOptionPane.QUESTION_MESSAGE);
    }

    @Override
    public void print(String s) {
        if (preventMultipleTasksLock.tryLock()) {
            bufferChanged = false;
            buffer += s;
            pool.execute(new printTask());
        }
        else {
            buffer += s;
            lock.lock();
            bufferChanged = true;
            lock.unlock();
        }
    }

    @Override
    public void println(String s) {
        print(s + "\n");
    }
    public static void main(String[] args) {
        String temp = JOptionPane.showInputDialog(null, "Enter your age", "Age Input Screen", JOptionPane.QUESTION_MESSAGE);
        JOptionPane.showMessageDialog(null, temp, "Age Output", JOptionPane.INFORMATION_MESSAGE);
    }


    public void close() throws IOException {
        pool.shutdown();
    }
    protected class printTask implements Runnable
    {

        public void run() {
            preventMultipleTasksLock.lock();
            do {
                try {lock.unlock();} catch (Exception e) {}
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e) {

                }
                lock.lock();
            } while ((bufferChanged == true) && (requestedRead == false));
            if (requestedRead == false) {
                String temp = buffer;
                buffer = "";
                bufferChanged = false;
                lock.unlock();
                JOptionPane.showMessageDialog(null, temp, "Output", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                lock.unlock();
            }
            preventMultipleTasksLock.unlock();
        }
    }
}