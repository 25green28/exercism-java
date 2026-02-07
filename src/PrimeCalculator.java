class PrimeCalculator {

    int nth(int nth) {
        if (nth < 1) {
            throw new IllegalArgumentException("nth must be a positive integer");
        }

        int n = 1;
        int num = 2;

        while (n < nth) {
            num++;

            if (isPrime(num)) {
                n++;
            }
        }

        return num;
    }

    private static boolean isPrime(int num) {
        int sqrt = (int) Math.sqrt(num);

        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        PrimeCalculator calculator = new PrimeCalculator();
        System.out.println(calculator.nth(4));
    }

}
