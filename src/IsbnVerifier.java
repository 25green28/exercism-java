class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        if (stringToVerify == null || stringToVerify.isEmpty()) {
            return false;
        }

        String cleaned = stringToVerify.replaceAll("-", "");
        if (cleaned.length() != 10) {
            return false;
        }

        int sum = 0;

        for (int i = 0; i < cleaned.length(); i++) {
            char c = cleaned.charAt(i);
            int value;

            if (c == 'X') {
                if (i != 9) {
                    return false;
                }
                value = 10;
            } else if (Character.isDigit(c)) {
                value = c - '0';
            } else {
                return false;
            }

            sum += value * (10 - i);
        }

        return sum % 11 == 0;
    }

    public static void main(String[] args) {
        IsbnVerifier isbnVerifier = new IsbnVerifier();
        System.out.println(isbnVerifier.isValid("3-598-2X507-9"));
    }

}
