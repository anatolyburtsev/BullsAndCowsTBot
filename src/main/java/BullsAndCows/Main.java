package BullsAndCows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

/**
 * Created by onotole on 9/22/16.
 */
public class Main {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new GameHandler());
        } catch (TelegramApiException e) {
            logger.error(e.toString());
        }
    }
}
