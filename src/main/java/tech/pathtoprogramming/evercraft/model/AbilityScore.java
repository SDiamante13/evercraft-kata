package tech.pathtoprogramming.evercraft.model;

import java.util.HashMap;
import java.util.Map;

public class AbilityScore {
    private final Map<Integer, Integer> scoreToModifierMap;
    private final int score;

    public AbilityScore(int score) {
        this.score = score;
        this.scoreToModifierMap = new HashMap<>();
        initializeScoreToModifierMap();
    }

    private void initializeScoreToModifierMap() {
        this.scoreToModifierMap.put(1, -5);
        this.scoreToModifierMap.put(2, -4);
        this.scoreToModifierMap.put(3, -4);
        this.scoreToModifierMap.put(4, -3);
        this.scoreToModifierMap.put(5, -3);
        this.scoreToModifierMap.put(6, -2);
        this.scoreToModifierMap.put(7, -2);
        this.scoreToModifierMap.put(8, -1);
        this.scoreToModifierMap.put(9, -1);
        this.scoreToModifierMap.put(10, 0);
        this.scoreToModifierMap.put(11, 0);
        this.scoreToModifierMap.put(12, 1);
        this.scoreToModifierMap.put(13, 1);
        this.scoreToModifierMap.put(14, 2);
        this.scoreToModifierMap.put(15, 2);
        this.scoreToModifierMap.put(16, 3);
        this.scoreToModifierMap.put(17, 3);
        this.scoreToModifierMap.put(18, 4);
        this.scoreToModifierMap.put(19, 4);
        this.scoreToModifierMap.put(20, 5);
    }

    public int score() {
        return score;
    }

    public int modifier() {
        return scoreToModifierMap.get(score);
    }
}
