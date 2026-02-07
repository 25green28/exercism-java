class LargestSeriesProductCalculator {
    private final String digitsSequenceToAnalyze;
    private static final String INPUT_LENGTH_EXCEPTION_MSG = "Series length must be less than or equal to the length of the string to search.";
    private static final String INPUT_SPAN_EXCEPTION_MSG = "Series length must be non-negative.";
    private static final String ONLY_DIGITS_EXCEPTION_MSG = "String to search may only contain digits.";

    LargestSeriesProductCalculator(String inputNumber) {
        for (char c : inputNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(ONLY_DIGITS_EXCEPTION_MSG);
            }
        }

        this.digitsSequenceToAnalyze = inputNumber;
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        if (numberOfDigits > digitsSequenceToAnalyze.length()) {
            throw new IllegalArgumentException(INPUT_LENGTH_EXCEPTION_MSG);
        }
        if (numberOfDigits < 1) {
            throw new IllegalArgumentException(INPUT_SPAN_EXCEPTION_MSG);
        }

        long result = 0;

        for (int i = 0; i <= digitsSequenceToAnalyze.length() - numberOfDigits; i++) {
            long product = 1;
            for (int y = i; y < i + numberOfDigits; y++) {
                product *= digitsSequenceToAnalyze.charAt(y) - '0';
            }
            if (product > result) {
                result = product;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LargestSeriesProductCalculator lsp = new LargestSeriesProductCalculator("63915");
        System.out.println(lsp.calculateLargestProductForSeriesLength(3));
    }
}
