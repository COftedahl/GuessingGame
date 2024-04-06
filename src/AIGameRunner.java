package src;

public class AIGameRunner {
    public static void main(String[] args) {
        NNAI ai = new NNAI(25,100,.001,.0001,.95,.005,new ComputerGuessingGameScorer(0,100),2,5,5,5,1);
        Game game = new TestIntelligentComputerPlay();
        ai.testGeneration(game);
        ai.evolveGeneration();
    }
}
