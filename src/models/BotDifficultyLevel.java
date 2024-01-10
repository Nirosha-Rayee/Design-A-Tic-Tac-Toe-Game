package models;

import strategy.BotPlayingStrategy;

public enum BotDifficultyLevel {
    EASY(1),
    MEDIUM(2),
    HARD(3);

    private final int value;

    private BotDifficultyLevel(int value) {
        this.value = value;
    }
    private int getValue() {
        return value;
    }


}
