package BullsAndCows;

/**
 * Created by onotole on 9/22/16.
 */
public class BullsCows {
    private int bulls;
    private int cows;
    private int size;

    private BullsCows(int bulls, int cows) {
        this.bulls = bulls;
        this.cows = cows;
        this.size = Games.getSize();
    }

    public static BullsCows getBullsAndCows(int pattern, int sample) {
        int[] bullsAndCows = countBullsAndCows(pattern, sample);
        return new BullsCows(bullsAndCows[0], bullsAndCows[1]);
    }

    public String printer() {
        if (bulls == size) {
            return Strings.win;
        } else {
            return printer(bulls, cows);
        }
    }

    public static String printer(int bulls, int cows) {
        return printBull(bulls) + " " + Strings.and + " " + printCow(cows);
    }

    public boolean isWin() {
        return bulls == size;
    }

    protected static int[] countBullsAndCows(String pattern, String sample) {
        for (int i = 0; i < pattern.length() - sample.length(); i++) {
            sample = "0" + sample;
        }
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == sample.charAt(i)) {
                bulls++;
            }
        }
        for (int i = 0; i < pattern.length(); i++) {
            for (int j = 0; j < pattern.length(); j++) {
                if (i != j && pattern.charAt(i) == sample.charAt(j) ) {
                    cows++;
                }
            }
        }

        return new int[]{bulls, cows};
    }

    protected static int[] countBullsAndCows(int pattern, int sample) {
        return countBullsAndCows("" + pattern, "" + sample);
    }

    private static String printBull(int count) {
        if (count == 0) {
            return count + " " + Strings.zeroBull;
        } else if (count == 1) {
            return count + " " + Strings.oneBull;
        } else {
            return count + " " + Strings.manyBulls;
        }
    }

    private static String printCow(int count) {
        if (count == 0) {
            return count + " " + Strings.zeroCow;
        } else if (count == 1) {
            return count + " " + Strings.oneCow;
        } else {
            return count + " " + Strings.manyCows;
        }
    }
}
