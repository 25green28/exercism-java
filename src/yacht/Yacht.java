package yacht;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Yacht {
    private final int score;

    Yacht(int[] dice, YachtCategory yachtCategory) {
        Map<Integer, Integer> numberOfNumbers = getNumberOfNumbers(dice);

        this.score = switch (yachtCategory) {
            case ONES -> numberOfNumbers.getOrDefault(1, 0);
            case TWOS -> numberOfNumbers.getOrDefault(2, 0) * 2;
            case THREES -> numberOfNumbers.getOrDefault(3, 0) * 3;
            case FOURS -> numberOfNumbers.getOrDefault(4, 0) * 4;
            case FIVES -> numberOfNumbers.getOrDefault(5, 0) * 5;
            case SIXES -> numberOfNumbers.getOrDefault(6, 0) * 6;

            case FULL_HOUSE -> {
                if (numberOfNumbers.containsValue(3) && numberOfNumbers.containsValue(2)) {
                    yield numberOfNumbers.entrySet().stream()
                            .mapToInt(e -> e.getKey() * e.getValue())
                            .sum();
                }
                yield 0;
            }

            case FOUR_OF_A_KIND -> numberOfNumbers.entrySet().stream()
                        .filter(e -> e.getValue() >= 4)
                        .mapToInt(e -> e.getKey() * 4).sum();

            case LITTLE_STRAIGHT -> {
                if (numberOfNumbers.keySet().equals(Set.of(1, 2, 3, 4, 5))) {
                    yield 30;
                }
                yield 0;
            }

            case BIG_STRAIGHT -> {
                if (numberOfNumbers.keySet().equals(Set.of(2, 3, 4, 5, 6))) {
                    yield 30;
                }
                yield 0;
            }

            case CHOICE -> numberOfNumbers.entrySet().stream()
                    .mapToInt(e -> e.getValue() * e.getKey())
                    .sum();

            case YACHT -> {
                if (numberOfNumbers.containsValue(5)) {
                    yield 50;
                }
                yield 0;
            }
        };
    }

    private Map<Integer, Integer> getNumberOfNumbers(int[] numbers) {
        Map<Integer, Integer> map = new HashMap<>();

        Arrays.stream(numbers).forEach(num -> map.compute(num, (k, v) -> v == null ? 1 : ++v));

        return Map.copyOf(map);
    }

    int score() {
        return score;
    }
}