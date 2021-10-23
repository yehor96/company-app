package company;

import java.util.Random;

public class Randomizer {

    private static final Random random = new Random();

    public static int getIntWithin(int lowBound, int topBound) {
        int value;
        do {
            value = random.nextInt(topBound + 1);
        } while (value < lowBound);
        return value;
    }

    public static boolean getBoolean() {
        return random.nextBoolean();
    }
}
