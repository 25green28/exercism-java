import java.util.HashMap;
import java.util.Map;

public class Say {

    private static final Map<Integer, String> NUMBERS = new HashMap<>();
    private static final Map<Integer, String> TENS = new HashMap<>();

    private static final String BILLION = " billion ";
    private static final String MILLION = " million ";
    private static final String THOUSAND = " thousand ";
    private static final String HUNDRED = " hundred ";

    static {
        NUMBERS.put(0, "zero");
        NUMBERS.put(1, "one");
        NUMBERS.put(2, "two");
        NUMBERS.put(3, "three");
        NUMBERS.put(4, "four");
        NUMBERS.put(5, "five");
        NUMBERS.put(6, "six");
        NUMBERS.put(7, "seven");
        NUMBERS.put(8, "eight");
        NUMBERS.put(9, "nine");
        NUMBERS.put(10, "ten");
        NUMBERS.put(11, "eleven");
        NUMBERS.put(12, "twelve");
        NUMBERS.put(13, "thirteen");
        NUMBERS.put(14, "fourteen");
        NUMBERS.put(15, "fifteen");
        NUMBERS.put(16, "sixteen");
        NUMBERS.put(17, "seventeen");
        NUMBERS.put(18, "eighteen");
        NUMBERS.put(19, "nineteen");
        TENS.put(2, "twenty");
        TENS.put(3, "thirty");
        TENS.put(4, "forty");
        TENS.put(5, "fifty");
        TENS.put(6, "sixty");
        TENS.put(7, "seventy");
        TENS.put(8, "eighty");
        TENS.put(9, "ninety");
    }


    public String say(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be positive or zero");
        }

        StringBuilder numberInWords = new StringBuilder();

        long billions = number / 1_000_000_000;
        if (billions != 0) {
            mapMaxThreeDigitsToWords((int) billions, BILLION, numberInWords);
            number %= 1_000_000_000;
        }

        long millions = number / 1_000_000;
        if (millions != 0) {
            mapMaxThreeDigitsToWords((int) millions, MILLION, numberInWords);
            number %= 1_000_000;
        }

        long thousands = number / 1_000;
        if (thousands != 0) {
            mapMaxThreeDigitsToWords((int) thousands, THOUSAND, numberInWords);
            number %= 1_000;
        }

        mapMaxThreeDigitsToWords((int) number, "", numberInWords);

        return numberInWords.toString().trim();
    }

    private void mapMaxThreeDigitsToWords(int num, String prefix, StringBuilder numberInWords) {
        if (num > 999) {
            throw new IllegalArgumentException("Number must be lower or equal to 999 to use this function!");
        }

        // Check hundreds
        if (num >= 100) {
            int hundreds = num / 100;
            num %= 100;
            numberInWords.append(NUMBERS.get(hundreds)).append(HUNDRED);
        }

        // Check the two last digits
        if (num < 20) {
            if (!(num == 0 && !numberInWords.isEmpty())) {
                numberInWords.append(NUMBERS.get(num)).append(prefix);
            }
        } else {
            int tens = num / 10;
            int ones = num % 10;
            if (ones == 0) {
                numberInWords.append(TENS.get(tens)).append(prefix);
            } else {
                numberInWords.append(TENS.get(tens)).append("-").append(NUMBERS.get(ones)).append(prefix);
            }
        }
    }

    public static void main(String[] args) {
        Say say = new Say();
        System.out.println(say.say(1000));
    }
}
