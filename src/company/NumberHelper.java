package company;

import java.util.Random;

public class NumberHelper {

    private static final Random random = new Random();

    public static int getRandomBounded(int lowBound, int topBound) {
        int value;
        do {
            value = random.nextInt(topBound);
        } while (value < lowBound);
        return value;
    }
}
