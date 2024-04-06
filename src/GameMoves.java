package src;

public class GameMoves {
    private int guess;
    private GameResultsImpl.Result result;
    public GameMoves(int g, GameResultsImpl.Result r) {
        guess = g;
        result = r;
    }
    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public GameResultsImpl.Result getResult() {
        return result;
    }

    public void setResult(GameResultsImpl.Result result) {
        this.result = result;
    }
}
