package yachtGame;

import java.util.ArrayList;
import java.util.List;

public class YachtHand {
    private List<Dice> dice;

    public YachtHand() {
        dice = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dice.add(new Dice());
        }
    }

    public void rollAll() {
        for (Dice die : dice) {
            die.roll();
        }
    }

    public void rollSelected(List<Integer> indices) {
        for (int index : indices) {
            dice.get(index).roll();
        }
    }

    public List<Dice> getDice() {
        return dice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Dice die : dice) {
            sb.append(die).append(" ");
        }
        return sb.toString().trim();
    }
}