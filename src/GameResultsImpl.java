package src;

import java.util.ArrayList;
import java.util.List;

public class GameResultsImpl implements GameResults{
    public enum Result {
        //indicates how player should adjust guesses to get closer to answer
        HIGHER,
        LOWER,
        CORRECT;
    }
    protected boolean wonGame = false;
    protected int numMovesUsed = -1;
    protected List<GameMoves> moves = null;
    public GameResultsImpl() {
        moves = new ArrayList<GameMoves>();
    }
    
}
