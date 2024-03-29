package domain.hangman;

import domain.randomWordPicker.RandomWordPicker;
import dto.NewGameDto;

public class HangmanGame {

    private final Round round;
    private final Life life;
    private final RandomWordPicker randomWordPicker;

    private Word word;
    private int gameCount;

    public HangmanGame(Round round, Life life, RandomWordPicker randomWordPicker) {
        this.round = round;
        this.life = life;
        this.randomWordPicker = randomWordPicker;
    }

    public boolean isRoundEnd() {
        return life.isZero() || word.isAllMatched();
    }

    public boolean isDone() {
        return round.isDone();
    }

    public NewGameDto setNewRound() {
        round.increase();
        life.recover();
        word = randomWordPicker.pick();
        gameCount = 1;

        return new NewGameDto(round.getCurrentRound(), word.length());
    }

    public void tryToMatch(Alphabet alphabet) {
        gameCount += 1;
        boolean result = word.tryToMatch(alphabet);
        if (!result) {
            life.decrease();
        }
    }

    public int getGameCount() {
        return gameCount;
    }

    public String getWord() {
        return word.getWord();
    }

    public boolean[] getIsMatched() {
        return word.getIsMatched();
    }

    public int getRemainingLife() {
        return life.getRemainingLife();
    }

    public boolean getResult() {
        return word.isAllMatched();
    }
}
