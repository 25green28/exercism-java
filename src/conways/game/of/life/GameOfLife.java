package conways.game.of.life;

class GameOfLife {
    public int[][] tick(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }

        final int rows = matrix.length;
        final int cols = matrix[0].length;

        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int neighbors = countAliveNeighbors(matrix, i, j, rows, cols);
                boolean isAlive = matrix[i][j] == 1;

                if (neighbors == 3 || (isAlive && neighbors == 2)){
                    result[i][j] = 1;
                }
            }
        }

        return result;
    }

    private int countAliveNeighbors(int[][] matrix, int x, int y, int rows, int cols) {
        int startRow = Math.max(0, x - 1);
        int endRow = Math.min(rows - 1, x + 1);
        int startCol = Math.max(0, y - 1);
        int endCol = Math.min(cols - 1, y + 1);

        int sum = 0;

        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if (i == x && j == y) continue;
                sum += matrix[i][j];
            }
        }

        return sum;
    }
}
