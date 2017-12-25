import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.List;

public class RaidPlanner extends TelegramLongPollingBot {

    private final String token = "??????????????";

    public String getBotToken() {
        return this.token;
    }

    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            long chatId = update.getMessage().getChatId();
            String userMessage = update.getMessage().getText();

            SendMessage botMessage = new SendMessage()
                    .setChatId(chatId)
                    .setText(userMessage);

            try {
                execute(botMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

    }

    public String getBotUsername() {
        return "RaidPlanner";
    }


    public void onUpdatesReceived(List<Update> updates) {

        for (Update update : updates) {
            onUpdateReceived(update);
        }

    }

    public void onClosing() {

    }



}
