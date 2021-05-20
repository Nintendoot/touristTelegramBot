package by.nintendo.touristtelegrambot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    private final CommandTouristTelegramBot commandTouristTelegramBot;

    @Value("${token}")
    private String botToken;
    @Value("${username}")
    private String botUsername;

    public Bot(CommandTouristTelegramBot commandTouristTelegramBot) {
        this.commandTouristTelegramBot = commandTouristTelegramBot;
    }


    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String inMessage = getInMessage(update);
        String chatId = getChatId(update);
        sendMsg(chatId, commandTouristTelegramBot.findBotCommand(inMessage));
    }


    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        setButtons(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error sending message.", e);
        }
    }

    private String getInMessage(Update update) {
        return update.hasEditedMessage() ?
                update.getEditedMessage().getText() :
                update.getMessage().getText();
    }

    private String getChatId(Update update) {
        return update.hasEditedMessage() ?
                update.getEditedMessage().getChatId().toString() :
                update.getMessage().getChatId().toString();
    }

    public synchronized void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow helpRow = new KeyboardRow();
        helpRow.add(new KeyboardButton("Help"));

        KeyboardRow allCitysRow = new KeyboardRow();
        allCitysRow.add(new KeyboardButton("All city"));

        keyboard.add(allCitysRow);
        keyboard.add(helpRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
    }


}
