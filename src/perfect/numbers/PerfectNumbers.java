package perfect.numbers;

enum Classification {
    DEFICIENT, PERFECT, ABUNDANT
}


class NaturalNumber {
    private int number;
    private int aliquotSum;

    NaturalNumber(int number) {
        if (number < 1)
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");

        this.number = number;
        aliquotSum = 0;

        for (int divisor = 1; divisor <= number / 2; divisor++) {
            if (number % divisor == 0) {
                aliquotSum += divisor;
            }
        }
    }

    Classification getClassification() {
        if (number == aliquotSum)
            return Classification.PERFECT;

        if (number < aliquotSum)
            return Classification.ABUNDANT;

        return Classification.DEFICIENT;
    }

    public static void main(String[] args) {
        NaturalNumber naturalNumber = new NaturalNumber(1);
        System.out.println(naturalNumber.getClassification());
    }
}
