package src;

public class GameMoves {
    private int guess;
    private GameResults.Result result;
    public GameMoves(int g, GameResults.Result r) {
        guess = g;
        result = r;
    }
    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public GameResults.Result getResult() {
        return result;
    }

    public void setResult(GameResults.Result result) {
        this.result = result;
    }
}
