package saddle.points;

import java.util.*;

record Point(int value, int row, int column){}

class Matrix {
    private final List<List<Integer>> values;

    Matrix(List<List<Integer>> values) {
        this.values = values;
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        List<Point> maxInRows = new ArrayList<>();
        List<Point> minInColumns = new ArrayList<>();

        Set<MatrixCoordinate> saddlePoints = new HashSet<>();

        if (!values.isEmpty()) {
            for (int rowIndex = 0; rowIndex < values.size(); rowIndex++) {
                maxInRows.addAll(getPoints(rowIndex, values.get(rowIndex).size(), true));
            }
        }

        if (!values.isEmpty()) {
            for (int columnIndex = 0; columnIndex < values.getFirst().size(); columnIndex++) {
                minInColumns.addAll(getPoints(columnIndex, values.size(), false));
            }
        }

        for (Point minInColumn : minInColumns) {
            for (Point maxInRow : maxInRows) {
                if (maxInRow.column() == minInColumn.column() &&
                        maxInRow.row() == minInColumn.row() &&
                        maxInRow.value() == minInColumn.value()
                ) {
                    saddlePoints.add(new MatrixCoordinate(maxInRow.column() + 1, maxInRow.row() + 1));
                }
            }
        }

        return saddlePoints;
    }

    private List<Point> getPoints(int index, int loopLimit, boolean addIfSmaller) {
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < loopLimit; i++) {
            int value = addIfSmaller ? values.get(index).get(i) : values.get(i).get(index);
            int row = addIfSmaller ? i : index;
            int column = addIfSmaller ? index : i;

            if (points.isEmpty()) {
                points.add(new Point(value, row, column));
                continue;
            }

            if (points.getFirst().value() == value) {
                points.add(new Point(value, row, column));
            }

            boolean condition = addIfSmaller ? points.getFirst().value() < value : points.getFirst().value() > value;

            if (condition) {
                points.clear();
                points.add(new Point(value, row, column));
            }
        }

        return points;
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix(Arrays.asList(
                Arrays.asList(9, 8, 7),
                Arrays.asList(5, 3, 2),
                Arrays.asList(6, 6, 7)
        ));

        System.out.println(matrix.getSaddlePoints());
    }
}
