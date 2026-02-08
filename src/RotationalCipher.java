class RotationalCipher {
    private static final int ALPHABET_SIZE = 26;
    private final int shiftKey;

    RotationalCipher(int shiftKey) {
        if (shiftKey < 0) {
            throw new IllegalArgumentException("shiftKey cannot be negative");
        }
        this.shiftKey = shiftKey % ALPHABET_SIZE;
    }

    String rotate(String data) {
        StringBuilder result = new StringBuilder();

        for (char c : data.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                result.append(rotate(c, 'a'));
            } else if (c >= 'A' && c <= 'Z') {
                result.append(rotate(c, 'A'));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    private char rotate(char c, char base) {
        return (char) (base + (c - base + shiftKey) % ALPHABET_SIZE);
    }

    public static void main(String[] args) {
        RotationalCipher cipher = new RotationalCipher(13);
        System.out.println(cipher.rotate("The quick brown fox jumps over the lazy dog."));
    }
}
