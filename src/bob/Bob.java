package bob;

class Bob {
    String hey(String input) {
        String textToCheck = input.trim();

        boolean isQuestion = !textToCheck.isEmpty() && textToCheck.charAt(textToCheck.length() - 1) == '?';
        boolean isAllCapitalLetters = isAllCapitalLetters(textToCheck);
        boolean silence = textToCheck.matches("\\s*");

        if (isQuestion && isAllCapitalLetters) {
            return "Calm down, I know what I'm doing!";
        } else if (isQuestion) {
            return "Sure.";
        } else if (isAllCapitalLetters) {
            return "Whoa, chill out!";
        } else if (silence) {
            return "Fine. Be that way!";
        } else {
            return "Whatever.";
        }
    }

    private boolean isAllCapitalLetters(String str) {
        boolean foundAtLeastOneCharacter = false;

        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                foundAtLeastOneCharacter = true;
                if (Character.isLowerCase(ch)) {
                    return false;
                }
            }
        }

        return foundAtLeastOneCharacter;
    }
}
