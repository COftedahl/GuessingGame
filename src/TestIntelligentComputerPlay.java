package src;

public class TestIntelligentComputerPlay extends SampleGuessingGameReworked {
    public static void main(String[] args) {
        ComputerStreamManager manager = (ComputerStreamManager) StreamFactory.getStreamManager(StreamFactory.StreamType.COMPUTER, StreamFactory.StreamType.COMPUTER);
        RunnableGuessingGame game = new RunnableGuessingGame(manager);
        synchronized (manager) {
            Thread thread = new Thread(game);
            //Thread inputResponder = new Thread(new RespondToInput(game, manager));
            //inputResponder.start();
            thread.start();
            while (game.askingToPlayAgain == false) {
                synchronized (manager) {
                    try {
                        manager.wait();
                        int num = (int) (Math.random() * game.RANGE  + game.BOTTOM);
                        manager.writeBuffer("" + num);
                        System.out.println("wroteToBuffer");
                    }
                    catch (Exception e) {
                        System.out.println("Error");
                    }
                }
            }
            try {
                java.lang.Thread.sleep(2000);
                thread.join();
                //inputResponder.join();
            }
            catch (Exception e) {

            }
        }
    }
    private static class RespondToInput implements Runnable {
        private RunnableGuessingGame game;
        ComputerStreamManager manager;
        protected RespondToInput(RunnableGuessingGame g, ComputerStreamManager c) {
            game = g;
            manager = c;
        }
        @Override
        public void run() {
            synchronized(manager) {
                while (game.askingToPlayAgain == false) {
                    try {
                        manager.wait();
                        if (game.askingToPlayAgain == false) {
                            //generate number, notify streamManager
                            int num = (int) (Math.random() * game.RANGE - game.BOTTOM);
                            manager.writeBuffer("" + num);
                            manager.notifyBuffer();
                        } else {
                            //end game, record results
                            System.out.println("Won game? " + game.wonGame);
                            manager.writeBuffer("no");
                            manager.notifyBuffer();
                        }
                    } catch (Exception e) {
                        System.out.println(e.getStackTrace());
                    }
                }
            }
            System.out.println("Exiting InputResponder");
        }
    }
}
