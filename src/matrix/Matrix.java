package matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Matrix {
    private final List<List<Integer>> matrix = new ArrayList<>();

    Matrix(String matrixAsString) {
        String[] array = matrixAsString.split("\n");

        for (String line : array) {
            String[] splittedLine = line.split(" ");
            List<Integer> row = new ArrayList<>();
            for (String s : splittedLine) {
                row.add(Integer.parseInt(s));
            }
            matrix.add(row);
        }
    }

    int[] getRow(int rowNumber) {
        return matrix.get(rowNumber - 1).stream().mapToInt(Integer::intValue).toArray();
    }

    int[] getColumn(int columnNumber) {
        int[] result = new int[matrix.size()];

        for (int i = 0; i < matrix.size(); i++) {
            result[i] = matrix.get(i).get(columnNumber - 1);
        }

        return result;
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix("1 2 \n10 20");
        System.out.println(Arrays.toString(matrix.getColumn(2)));
    }
}
