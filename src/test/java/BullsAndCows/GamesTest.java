package BullsAndCows;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by onotole on 9/24/16.
 */
public class GamesTest {
    private Games games;
    private Long chatId1 = 100L;
    private int number;

    @Before
    public void setUp() {
        games = new Games();
        games.startNewGame(chatId1);
        number = games.getNumberByChatId(chatId1);
    }

    @Test
    public void tryToGuessWin() throws Exception {
        assertThat(games.tryToGuess(chatId1, number), equalTo(Strings.win));
    }

    @Test
    public void isItNumberWithGoodLengthPositive() throws Exception {
        assertThat(Games.isItNumberWithGoodLength("1243"), equalTo(true));
    }

    @Test
    public void isItNumberWithGoodLengthNegative1() throws Exception {
        assertThat(Games.isItNumberWithGoodLength("123"), equalTo(false));
    }

    @Test
    public void isItNumberWithGoodLengthNegative2() throws Exception {
        assertThat(Games.isItNumberWithGoodLength("12a3"), equalTo(false));
    }

    @Test
    public void isDigitsDublicatePositive1() throws Exception {
        assertThat(Games.isDigitsDublicate(1505), equalTo(true));
    }

    @Test
    public void isDigitsDublicatePositive2() throws Exception {
        assertThat(Games.isDigitsDublicate("0000"), equalTo(true));
    }

    @Test
    public void isDigitsDublicateNegative1() throws Exception {
        assertThat(Games.isDigitsDublicate(1502), equalTo(false));
    }

    @Test
    public void isDigitsDublicateNegative2() throws Exception {
        assertThat(Games.isDigitsDublicate("9870134"), equalTo(false));
    }

    @Test
    public void generateNewNumber() throws Exception {
        boolean isOk = true;
        int number;
        for (int i = 0; i < 1000; i++) {
            number = Games.generateNewNumber(4);
            if (number > 9999 || number < 1000 || Games.isDigitsDublicate(number)) {
                isOk = false;
                break;
            }
        }
        assertThat(isOk, equalTo(true));
    }

    @Test
    public void switchInArray() throws Exception {
        int[] array = new int[] {1, 2, 3};
        int[] expectedArray = new int[] {2, 1, 3};
        assertArrayEquals(Games.switchInArray(array, 0, 1), expectedArray);
    }

}