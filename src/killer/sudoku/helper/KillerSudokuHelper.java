package killer.sudoku.helper;

import java.util.ArrayList;
import java.util.List;

// TODO: THIS EXERCISE IS NOT FINISHED!
public class KillerSudokuHelper {

    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize, List<Integer> exclude) {
        throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
    }

    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize) {
        String startNumber = "";
        String endNumber = "";
        List<List<Integer>> combinations = new ArrayList<>();

        for (int i = 1; i <= cageSize; i++) {
            startNumber = startNumber.concat(String.valueOf(i));
        }

        for (int i = 9; i >  9 - cageSize; i--) {
            endNumber = endNumber.concat(String.valueOf(i));
        }
        int value = Integer.parseInt(startNumber);
        int startValue = value;
        int endValue = Integer.parseInt(endNumber);

        for (int i = startValue; i <= endValue / 2; i++) {
            String number = String.valueOf(i);
            int zeroIndex = number.indexOf('0');
            if (zeroIndex != -1) {
                i += (int) Math.pow(10, cageSize - zeroIndex - 1);
                continue;
            }

            int sum = 0;

            for (char ch :  number.toCharArray()) {
                sum += Character.getNumericValue(ch);
            }

            if (sum == cageSum) {
                List<Integer> combination = new ArrayList<>();
                boolean flag = true;
                for (char ch :  number.toCharArray()) {
                    if (combination.contains(Character.getNumericValue(ch))) {
                        flag = false;
                        break;
                    }
                    combination.add(Character.getNumericValue(ch));
                }

                if (flag) {
                    combinations.add(combination);
                }
            }

        }

        System.out.println(value);
        System.out.println(endValue);

        return combinations;
    }

    public static void main(String[] args) {
        KillerSudokuHelper killerSudokuHelper = new KillerSudokuHelper();
        System.out.println(killerSudokuHelper.combinationsInCage(10, 2));
    }

}
