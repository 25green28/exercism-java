package kindergarten.garden;

import java.util.*;

public class KindergartenGarden {
    private final Map<String, List<Plant>> kidsPlants = new HashMap<>();

    private static final String[] KID_NAMES = { "Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet",
            "Ileana",  "Joseph", "Kincaid", "Larry" };

    private static final String INVALID_GARDEN = "Invalid garden format was provided";

    KindergartenGarden(String garden) {
        String[] rows = garden.split("\n");
        if (rows.length != 2 ||
                rows[0].length() % 2 != 0 ||
                rows[1].length() % 2 != 0) {

            throw new IllegalArgumentException(INVALID_GARDEN);
        }

        for (String row : rows) {
            if (row.length() / 2 > KID_NAMES.length) {
                throw new IllegalArgumentException(INVALID_GARDEN);
            }
            for (int kid = 0; kid < row.length() / 2; kid++) {
                int index = kid * 2;
                Plant plant1 = Plant.getPlant(row.charAt(index));
                Plant plant2 = Plant.getPlant(row.charAt(index + 1));

                if (plant1 == null || plant2 == null) {
                    throw new IllegalArgumentException(INVALID_GARDEN);
                }

                kidsPlants.computeIfAbsent(KID_NAMES[kid], k -> new ArrayList<>())
                        .addAll(List.of(plant1, plant2));
            }
        }
    }

    List<Plant> getPlantsOfStudent(String student) {
        return kidsPlants.get(student);
    }

    public static void main(String[] args) {
        KindergartenGarden kindergartenGarden = new KindergartenGarden("VVCCGG\nVVCCGG");
        System.out.println(kindergartenGarden.getPlantsOfStudent("Alice"));
        System.out.println(kindergartenGarden.getPlantsOfStudent("Bob"));
    }

}

enum Plant {
    VIOLETS,
    RADISHES,
    CLOVER,
    GRASS;

    static Plant getPlant(char plantCode) {
        switch (plantCode) {
            case 'G':
                return GRASS;
            case 'C':
                return CLOVER;
            case 'R':
                return RADISHES;
            case 'V':
                return VIOLETS;
        }

        return null;
    }
}
