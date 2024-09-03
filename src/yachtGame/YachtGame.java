package yachtGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class YachtGame {
    private YachtHand hand;
    private Map<String, Integer> scoreCard;

    public YachtGame() {
        hand = new YachtHand();
        scoreCard = new HashMap<>();
        initializeScoreCard();
    }

    private void initializeScoreCard() {
        scoreCard.put("ones", 0);
        scoreCard.put("twos", 0);
        scoreCard.put("threes", 0);
        scoreCard.put("fours", 0);
        scoreCard.put("fives", 0);
        scoreCard.put("sixes", 0);
        // Add more categories as needed
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Roll dice? (y/n): ");
            if (scanner.next().equalsIgnoreCase("y")) {
                hand.rollAll();
                System.out.println("Dice rolled: " + hand);
            } else {
                System.out.println("Which dice to roll (0-4)? Separate indices with spaces: ");
                scanner.nextLine(); // Consume leftover newline
                String[] indices = scanner.nextLine().split(" ");
                hand.rollSelected(parseIndices(indices));
                System.out.println("Selected dice rolled: " + hand);
            }
        }

        // Score calculation example
        System.out.println("Enter category to score (ones, twos, threes, fours, fives, sixes): ");
        String category = scanner.next();
        int score = calculateScore(category);
        scoreCard.put(category, score);
        System.out.println("Score for " + category + ": " + score);
        System.out.println("Current scorecard: " + scoreCard);
    }

    private List<Integer> parseIndices(String[] indices) {
        List<Integer> result = new ArrayList<>();
        for (String index : indices) {
            result.add(Integer.parseInt(index));
        }
        return result;
    }

    private int calculateScore(String category) {
        int score = 0;
        for (Dice die : hand.getDice()) {
            if (die.getValue() == getCategoryValue(category)) {
                score += die.getValue();
            }
        }
        return score;
    }

    private int getCategoryValue(String category) {
        switch (category) {
            case "ones": return 1;
            case "twos": return 2;
            case "threes": return 3;
            case "fours": return 4;
            case "fives": return 5;
            case "sixes": return 6;
            default: throw new IllegalArgumentException("Invalid category");
        }
    }

    public static void main(String[] args) {
        YachtGame game = new YachtGame();
        game.play();
    }
}