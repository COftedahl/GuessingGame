package src;

public class TestIntelligentComputerPlay extends SampleGuessingGameReworked {
    public static void main(String[] args) {
        ComputerStreamManager manager = (ComputerStreamManager) StreamFactory.getStreamManager(StreamFactory.StreamType.COMPUTER, StreamFactory.StreamType.COMPUTER);
        RunnableGuessingGame game = new RunnableGuessingGame(manager);
        synchronized (manager) {

            Thread thread = new Thread(game);
            thread.start();

            while (game.askingToPlayAgain == false) {
                synchronized (manager) {
                    try {
                        manager.wait();
                        if (game.askingToPlayAgain == false) {
                            manager.writeBuffer((generateRandomNumber(game.BOTTOM, game.TOP)));
                        }
                        else {
                            manager.writeBuffer("No");
                        }
                    } catch (InterruptedException e) {

                    }
                }
            }

        }
    }

    private static String generateRandomNumber(int bottom, int top) {
        return ("" + (int)(Math.random() * (top - bottom + 1) + bottom));
    }
}
/*try {
                        //manager.lock.lock();
                        synchronized(manager.buffer) {
                            manager.wait();
                            int num = (int) (Math.random() * game.RANGE + game.BOTTOM);
                            manager.writeBuffer("" + num);
                            System.out.println("wroteToBuffer");
                            manager.buffer.notify();
                        }
                        //manager.lock.unlock();
                    }
                    catch (Exception e) {
                        System.out.println("Error");
                    }*/
