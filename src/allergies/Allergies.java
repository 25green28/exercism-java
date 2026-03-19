package allergies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Allergies {
    private final List<Allergen> allergens = new ArrayList<>();
    private static final Map<Integer, Allergen> allergenMap = Map.of(
            1, Allergen.EGGS,
            2, Allergen.PEANUTS,
            3, Allergen.SHELLFISH,
            4, Allergen.STRAWBERRIES,
            5, Allergen.TOMATOES,
            6, Allergen.CHOCOLATE,
            7, Allergen.POLLEN,
            8, Allergen.CATS
    );

    Allergies(int score) {
        for (int i = 1; i < 9; i++) {
            boolean isAllergic = (score & 1) == 1;
            if (isAllergic) {
                allergens.add(allergenMap.get(i));
            }
            score = score >> 1;
        }
    }

    boolean isAllergicTo(Allergen allergen) {
        return allergens.contains(allergen);
    }

    List<Allergen> getList() {
        return allergens;
    }

    public static void main(String[] args) {
        Allergies allergies = new Allergies(1);
        System.out.println(allergies.getList());
    }
}
