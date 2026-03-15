package sum.of.multiples;

import java.util.HashSet;
import java.util.Set;

class SumOfMultiples {
    private final int sum;

    SumOfMultiples(int level, int[] magicalItemsValues) {
        Set<Integer> allValues = new HashSet<>();

        for (int magicalItemValue : magicalItemsValues) {
            allValues.addAll(calculateMultiples(magicalItemValue, level));
        }

        this.sum = allValues.stream().mapToInt(Integer::intValue).sum();
    }

    int getSum() {
        return sum;
    }

    private Set<Integer> calculateMultiples(int number, int maxRange) {
        Set<Integer> multiples = new HashSet<>();

        if (number < 1) {
            return multiples;
        }

        for (int i = number; i < maxRange; i += number) {
            multiples.add(i);
        }

        return multiples;
    }

    public static void main(String[] args) {
        SumOfMultiples sumOfMultiples = new SumOfMultiples(1, new int[]{0});
        System.out.println(sumOfMultiples.getSum());
    }
}

