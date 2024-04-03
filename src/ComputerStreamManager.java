package src;

public class ComputerStreamManager extends AbstractStreamManager{
    private String buffer = "";

    public void writeBuffer(String s) {  buffer = s;  }
    public void notifyBuffer() {
        synchronized (buffer) {
            buffer.notify();
        }
    }
    @Override
    public String read() {
        synchronized(this) {
            notify();
            try {
                wait(200);
            }
            catch (Exception e) {

            }
            return (buffer.length()>0?buffer:"1");
        }
    }

    @Override
    public void print(String s) {
        System.out.print(s);
    }

    @Override
    public void println(String s) {
        print(s + "\n");
    }

    @Override
    public void close(){

    }
}
