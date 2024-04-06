package src;

public class ComputerGuessingGameScorer extends ScoringAlgorithm {
    public ComputerGuessingGameScorer(int min, int max) {
        super(min, max);
    }

    public double scoreGame(GameResultsImpl results) {
        double score = 0;
        int topRecommended = 100;
        int bottomRecommended = 1;
        for (int i = 0; i < results.moves.size(); i += 1) {
            if ((results.moves.get(i).getGuess() <= topRecommended) &&
                (results.moves.get(i).getGuess() >= bottomRecommended)) {
                score += 2;
                if (results.moves.get(i).getResult() == GameResultsImpl.Result.LOWER) {
                    topRecommended = results.moves.get(i).getGuess();
                }
                else if (results.moves.get(i).getResult() == GameResultsImpl.Result.HIGHER) {
                    bottomRecommended = results.moves.get(i).getGuess();
                }
            }
        }
        if (results.wonGame == true) {
            score += 25;
        }
        return score;
    }
}
