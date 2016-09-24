package BullsAndCows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 * Created by onotole on 9/22/16.
 */
public class GameHandler extends TelegramLongPollingBot {
    private Games games;
    Logger logger = LoggerFactory.getLogger(GameHandler.class);

    public GameHandler() {
        super();
        games = new Games();
    }

    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();
            logger.info("Message: " + message.getText() + ". From: " + message.getFrom().toString() + ". ChatId: "
                    + message.getChatId());
            String response;
            String command;
            String argument = null;
            String[] messageParts;
            if (message.getText() != null && message.getText().contains(" ")) {
                messageParts = message.getText().split(" ");
                command = messageParts[0];
                if (messageParts.length > 1) {
                    argument = messageParts[1];
                }
            } else {
                command = message.getText();
            }
            if (command.contains("@")) {
                command = command.split("@")[0];
            }

            if(message.hasText()){
                if (Commands.start.equals(command)) {
                    response = games.startNewGame(message.getChatId());
                } else if (isPhraseDigits(command)) {
                    response = games.tryToGuess(message.getChatId(), command);
                } else if (isPhraseDigits(argument)) {
                    response = games.tryToGuess(message.getChatId(), argument);
                } else if (Commands.help.equals(command)){
                    response = Strings.help;
                } else {
                    return;
                }
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString());
                sendMessageRequest.setText(response);
                try {
                    sendMessage(sendMessageRequest);
                } catch (TelegramApiException e) {
                    logger.error(e.toString());
                }
            }
        }
    }

    public String getBotUsername() {
        return BotConfig.BOT_USERNAME;
    }

    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    private boolean isPhraseDigits(String number) {
        if (number == null || number.length() != Games.getSize()) return false;
        for (char ch: number.toCharArray()) {
            if (! Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }
}
