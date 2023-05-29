package src.hangmanGame;

import java.util.Iterator;
import java.util.Map;

public class HangmanGameRoundTable implements Iterable<Integer> {

    private final Map<Integer, HangmanGameRoundInfo> roundTable;
    private String gameResult;

    public HangmanGameRoundTable(Map<Integer, HangmanGameRoundInfo> roundTable) {
        this.roundTable = roundTable;
    }

    public void saveRound(int round, HangmanGameRoundInfo roundInfo) {
        roundTable.put(round, roundInfo);
    }

    public HangmanGameRoundInfo getRound(int round) {
        return roundTable.get(round);
    }

    public void saveGameResult(String gameResult) {
        this.gameResult = gameResult;
    }

    public String gameResutToString() {
        StringBuilder result = new StringBuilder();
        result.append("\n=== Game Result ===\n").append(gameResult);
        for (Integer roundId : roundTable.keySet()){
            result.append(roundTable.get(roundId).toString());
        }
        return result.append("===================\n").toString();
    }

    @Override
    public Iterator<Integer> iterator() {
        return roundTable.keySet().iterator();
    }

    static class HangmanGameRoundInfo {

        private static final String INFO_PRINT_FORMAT = "남은 목숨 : %d, %s, 사용자 입력 : %c\n";

        public final int remainingLife;
        public final String correctingWord;
        public final char inputAlphabet;

        public HangmanGameRoundInfo(int remainingLife, String correctingWord, char inputAlphabet) {
            this.remainingLife = remainingLife;
            this.correctingWord = correctingWord;
            this.inputAlphabet = inputAlphabet;
        }

        @Override
        public String toString() {
            return String.format(INFO_PRINT_FORMAT, remainingLife, correctingWord, inputAlphabet);
        }
    }
}
