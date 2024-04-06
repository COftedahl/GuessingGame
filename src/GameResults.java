package src;

import java.util.ArrayList;
import java.util.List;

public class GameResults {
    public enum Result {
        HIGHER,
        LOWER,
        CORRECT;
    }
    protected boolean wonGame = false;
    protected int numMovesUsed = -1;
    protected List<GameMoves> moves = null;
    public GameResults() {
        moves = new ArrayList<GameMoves>();
    }
    
}
