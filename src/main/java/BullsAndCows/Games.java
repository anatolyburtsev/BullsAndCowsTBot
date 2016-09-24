package BullsAndCows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by onotole on 9/22/16.
 */
public class Games {
    private static int size = 4;
    private static int[] possibleDigits = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private Map<Long, Integer> currentGames;
    private static Random random;
    Logger logger = LoggerFactory.getLogger(Games.class);

    public Games() {
        currentGames = new HashMap<Long, Integer>();
    }

    public String startNewGame(Long chatId) {
        String response;
        if (isGameExist(chatId)) {
            logger.info("chatId: " + chatId + " tried to start new game twice!");
            response = Strings.gameAlreadyStarted;
        } else {
            int number = generateNewNumber(size);
            logger.info("started new game for chatId: " + chatId + ". Number: " + number);
            currentGames.put(chatId, number);
            response = Strings.startGame;
        }
        return response;
    }

    public String tryToGuess(Long chatId, String number) {
        logger.info("attempt to win by chatId: " + chatId + " with number: " + number +
                ". Number: " + getNumberByChatId(chatId));
        if (! isGameExist(chatId)) {
            logger.info("chatId " + chatId + " tried to guess without started game");
            return Strings.notStartYet;
        }
        if (! isItNumberWithGoodLength(number)) {
            logger.info("chatId " + chatId + " tried to input bad number: " + number);
            return Strings.useCorrectNumber;
        }
        if (isDigitsDublicate(number)) {
            logger.info("chatId " + chatId + " input number with duplicates digits: " + number);
            return Strings.dublicateProhibited;
        }
        int pattern = currentGames.get(chatId);
        BullsCows bullsAndCows = BullsCows.getBullsAndCows(pattern, Integer.parseInt(number));
        if (bullsAndCows.isWin()) {
            logger.info("chatId: " + chatId + " win! Number: " + number);
            currentGames.remove(chatId);
        }
        return bullsAndCows.printer();
    }

    public String tryToGuess(Long chatId, int number) {
        return tryToGuess(chatId, "" + number);
    }

    public static int getSize() {
        return size;
    }

    protected int getNumberByChatId(Long chatId) {
        //only for test
        return currentGames.get(chatId);
    }

    protected static boolean isItNumberWithGoodLength(String number) {
        if (number.length() != Games.getSize() ) return false;
        for (int i = 0; i < number.length(); i++) {
            if (! Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    protected static boolean isDigitsDublicate(String number) {
        int[] digits = new int[10];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = 0;
        }
        for (int i = 0; i < number.length(); i++) {
            String letter = String.valueOf(number.charAt(i));
            digits[Integer.parseInt(letter)]++;
        }
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] > 1) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isDigitsDublicate(int number) {
        return isDigitsDublicate("" + number);
    }

    protected static int generateNewNumber(int size) {
        int result = 0;
        int tmpPosition;
        if (random == null) random = new Random();
        for (int i = 0; i < size; i++) {
            tmpPosition = random.nextInt(possibleDigits.length - i);
            result *= 10;
            result += possibleDigits[tmpPosition];
            switchInArray(possibleDigits, tmpPosition, possibleDigits.length - 1 - i);
        }
        if (result < 1000) {
            result += possibleDigits[0]*1000;
        }
        return result;
    }

    protected static int[] switchInArray(int[] arr, int pos1, int pos2) {
        int tmp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = tmp;
        return arr;
    }

    private boolean isGameExist(Long chatId) {
        return currentGames.containsKey(chatId);
    }

}
