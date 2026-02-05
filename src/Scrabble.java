class Scrabble {
    private int score = 0;

    Scrabble(String word) {
        for (char c : word.toUpperCase().toCharArray()) {
            switch (c) {
                case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' -> score += 1;
                case 'D', 'G' -> score += 2;
                case 'B', 'C', 'M', 'P' -> score += 3;
                case 'F', 'H', 'V', 'W', 'Y' -> score += 4;
                case 'K' -> score += 5;
                case 'J', 'X' -> score += 8;
                case 'Q', 'Z' -> score += 10;
            }
        }
    }

    int getScore() {
        return score;
    }
}
