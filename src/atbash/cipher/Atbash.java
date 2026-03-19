package atbash.cipher;

class Atbash {
    String encode(String input) {
        StringBuilder codedMessage = invertChars(input.toLowerCase().toCharArray());

        // Add spaces
        int index = 5;
        while (index < codedMessage.length()) {
            codedMessage.insert(index, ' ');
            index += 6;
        }

        return codedMessage.toString();
    }

    String decode(String input) {
        return invertChars(input.toCharArray()).toString();
    }

    private StringBuilder invertChars(char[] chars) {
        StringBuilder output = new StringBuilder();

        for (char ch : chars) {
            if (Character.isLetter(ch)) {
                output.append(codeChar(ch));
            }
            if (Character.isDigit(ch)) {
                output.append(ch);
            }
        }

        return output;
    }

    private char codeChar(char ch) {
        return (char) ('z' - ch + 'a');
    }
}

