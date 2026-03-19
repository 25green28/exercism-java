package twelve.days;

class TwelveDays {
    private static final String[] numberToCardinal = {
            "one", "two", "three", "four", "five", "six",
            "seven", "eight", "nine", "ten", "eleven", "twelve"
    };

    private static final String[] numberToOrdinal = {
            "first", "second", "third", "fourth", "fifth", "sixth",
            "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"
    };

    private static final String[] gifts = {
            "a Partridge in a Pear Tree",
            "Turtle Doves",
            "French Hens",
            "Calling Birds",
            "Gold Rings",
            "Geese-a-Laying",
            "Swans-a-Swimming",
            "Maids-a-Milking",
            "Ladies Dancing",
            "Lords-a-Leaping",
            "Pipers Piping",
            "Drummers Drumming"
    };

    String verse(int verseNumber) {
        StringBuilder verse = new StringBuilder();

        verse.append("On the ")
                .append(numberToOrdinal[verseNumber - 1])
                .append(" day of Christmas my true love gave to me: ");

        for (int i = verseNumber - 1; i >= 0; i--) {
            String space;

            // Decide the space after the gift message
            if (i == 0) {
                space = ".";
            } else {
                space = (i == 1) ? ", and " : ", ";
            }

            // Add the cardinal number before, if the verse number isn't the last
            if  (i != 0) {
                verse.append(numberToCardinal[i]).append(" ");
            }

            // Add the gift message and append the space
            verse.append(gifts[i]).append(space);
        }

        verse.append("\n");

        return verse.toString();
    }

    String verses(int startVerse, int endVerse) {
        StringBuilder verses = new StringBuilder();

        for (int i = startVerse; i <= endVerse; i++) {
            verses.append(verse(i));

            // Add another space, if the verse isn't the last one
            if (i != endVerse) {
                verses.append("\n");
            }
        }

        return verses.toString();
    }

    String sing() {
        return verses(1, 12);
    }
}