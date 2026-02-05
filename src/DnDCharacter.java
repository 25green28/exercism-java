import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class DnDCharacter {
    final static Random random = new Random();

    private int strength = 0;
    private int dexterity = 0;
    private int constitution = 0;
    private int intelligence = 0;
    private int wisdom = 0;
    private int charisma = 0;

    int ability(List<Integer> scores) {
        AtomicInteger sum = new AtomicInteger(0);
        scores.stream().sorted(Comparator.reverseOrder()).limit(3).forEach(sum::getAndAdd);
        return sum.get();
    }

    List<Integer> rollDice() {
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            scores.add(random.nextInt(1, 7));
        }
        return scores;
    }

    int modifier(int input) {
        return (int) Math.floor((double) (input - 10) / 2);
    }

    int getStrength() {
        if (strength == 0) {
            strength = ability(rollDice());
        }
        return strength;
    }

    int getDexterity() {
        if (dexterity == 0) {
            dexterity = ability(rollDice());
        }
        return dexterity;
    }

    int getConstitution() {
        if (constitution == 0) {
            constitution = ability(rollDice());
        }
        return constitution;
    }

    int getIntelligence() {
        if (intelligence == 0) {
            intelligence = ability(rollDice());
        }
        return intelligence;
    }

    int getWisdom() {
        if (wisdom == 0) {
            wisdom = ability(rollDice());
        }
        return wisdom;
    }

    int getCharisma() {
        if (charisma == 0) {
            charisma = ability(rollDice());
        }
        return charisma;
    }

    int getHitpoints() {
        return 10 + modifier(getConstitution());
    }
}
