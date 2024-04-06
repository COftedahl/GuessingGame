package src;

import java.util.ArrayList;

public class ScoredNeuralNetwork {
    private NeuralNetwork nn;
    private ScoringAlgorithm scoringAlgorithm;
    private ArrayList<GameResultsImpl> results;
    private double avgScore;

    public ScoredNeuralNetwork(ScoringAlgorithm scoringAlgorithm, int ... layers) {
        this.scoringAlgorithm = scoringAlgorithm;
        nn = new NeuralNetwork(layers);
        avgScore = scoringAlgorithm.getMinScore();
        results = new ArrayList<GameResultsImpl>();
    }

    public NeuralNetwork getNN() {
        return nn;
    }

    public void setNN(NeuralNetwork nn) {
        this.nn = nn;
    }

    public double scoreNN(GameResultsImpl results) {
        return scoringAlgorithm.scoreGame(results);
    }

    public ScoringAlgorithm getScoringAlgorithm() {
        return scoringAlgorithm;
    }

    public void setScoringAlgorithm(ScoringAlgorithm scoringAlgorithm) {
        this.scoringAlgorithm = scoringAlgorithm;
    }

    public void saveResults(GameResultsImpl res) {
        results.add(res);
    }
    public void clearResults() {
        results.clear();
    }
    public ArrayList<GameResultsImpl> getResults() {
        return results;
    }
    public void setResults(ArrayList<GameResultsImpl> results) {
        this.results = results;
    }

    public double getAvgScore() {
        return avgScore;
    }
    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }
}
