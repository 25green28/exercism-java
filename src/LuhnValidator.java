import java.util.ArrayList;
import java.util.List;

class LuhnValidator {

    boolean isValid(String candidate) {
        String candidateToVerify = candidate.replace(" ", "");
        if (candidateToVerify.length() < 2) {
            return false;
        }

        List<Integer> digits = new ArrayList<>();

        for (char c : candidateToVerify.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }

            digits.add(c - '0');
        }

        digits = digits.reversed();

        int sum = 0;
        for (int i = 0; i < digits.size(); i++) {
            int digit = digits.get(i);
            if (i % 2 == 1) {
                int doubledDigit = digit * 2;
                if (doubledDigit > 9) {
                    doubledDigit -= 9;
                }
                sum += doubledDigit;
            } else {
                sum += digit;
            }
        }

        return sum % 10 == 0;
    }

    public static void main(String[] args) {
        LuhnValidator lv = new LuhnValidator();
        System.out.println(lv.isValid("4539 3195 0343 6467"));
    }

}
