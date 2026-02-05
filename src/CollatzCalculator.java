class CollatzCalculator {

    int computeStepCount(int start) {
        if (start < 1) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }

        int steps = 0;
        int n = start;

        while (n > 1) {
            n = (n % 2 == 0) ? n / 2 : n * 3 + 1;
            steps++;
        }

        return steps;
    }
}
