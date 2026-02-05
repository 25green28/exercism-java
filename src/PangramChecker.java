public class PangramChecker {
    public boolean isPangram(String input) {
        String lowerCaseInput = input.toLowerCase();

        for (int i = 97; i < 123; i++) {
            if (lowerCaseInput.indexOf((char) i) == -1) {
                return false;
            }
        }

        return true;
    }
}

class Test {
    public static void main(String[] args) {
        PangramChecker checker = new PangramChecker();
        System.out.println(checker.isPangram("the_quick_brown_fox_jumps_over_the_lazy_dog"));
    }
}