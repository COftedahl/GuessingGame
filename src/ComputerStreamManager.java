package src;

public class ComputerStreamManager extends AbstractStreamManager{
    protected String buffer = "";

    public void writeBuffer(String s) {
        buffer = s;
        synchronized (this) {
            this.notify();
        }
    }
    @Override
    public String read() {
        synchronized(this) {
            try {
                notify();
                wait();
                return buffer;
            }
            catch (Exception e) {

            }
        }
        return "-1";
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
