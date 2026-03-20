package saddle.points;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

class Matrix {
    private final int rows;
    private final int columns;
    private final int[] rowsMax;
    private final int[] columnsMin;

    Matrix(List<List<Integer>> values) {
        rows = values.size();
        columns = rows > 0 ? values.getFirst().size() : 0;

        rowsMax = values.stream().mapToInt(
                row -> row.stream()
                        .mapToInt(Integer::intValue)
                        .max().orElseThrow()
        ).toArray();

        columnsMin = range(0, columns).map(
                col -> range(0, rows)
                        .map(i -> values.get(i).get(col))
                        .min().orElseThrow()
        ).toArray();
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        return range(0, rows).boxed()
                .flatMap(row -> range(0, columns)
                        .filter(columns -> rowsMax[row] == columnsMin[columns])
                        .mapToObj(col -> new MatrixCoordinate(row + 1, col + 1)))
                .collect(Collectors.toSet());
    }
}
