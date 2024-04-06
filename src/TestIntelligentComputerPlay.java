package src;


public class TestIntelligentComputerPlay extends SampleGuessingGameReworked implements Game{
    public GameResultsImpl gamePlay(NeuralNetwork network) {
        ComputerStreamManager manager = (ComputerStreamManager) StreamFactory.getStreamManager(StreamFactory.StreamType.COMPUTER, StreamFactory.StreamType.COMPUTER);
        RunnableGuessingGame game = new RunnableGuessingGame(manager);
        GameResultsImpl results = new GameResultsImpl();
        double[] inputs = {1,100};
        synchronized (manager) {

            Thread thread = new Thread(game);
            thread.start();

            while (game.askingToPlayAgain == false) {
                synchronized (manager) {
                    try {
                        manager.wait();
                        results = game.getResults();
                        if (game.askingToPlayAgain == false) {
                            if ((results.moves.size() > 0) &&
                                (results.moves.get(results.moves.size() - 1) != null)) {
                                if (results.moves.get(results.moves.size() - 1).getResult() == GameResultsImpl.Result.HIGHER) {
                                    inputs[0] = results.moves.get(results.moves.size() - 1).getGuess();
                                }
                                else if (results.moves.get(results.moves.size() - 1).getResult() == GameResultsImpl.Result.LOWER) {
                                    inputs[1] = results.moves.get(results.moves.size() - 1).getGuess();
                                }
                            }
                            manager.writeBuffer("" + network.compute(inputs, 1, 100));
                        }
                        else {
                            manager.writeBuffer("No");
                        }
                    } catch (InterruptedException e) {

                    }
                }
            }

        }
        return results;
    }

    private static String generateRandomNumber(int bottom, int top) {
        return ("" + (int)(Math.random() * (top - bottom + 1) + bottom));
    }

    public GameResultsImpl play(NeuralNetwork n) {
        return gamePlay(n);
    }
}
