import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class RaidPlanner extends TelegramLongPollingBot {

	private final static String token = "??????????????";
	private final static Logger logger = LogManager.getLogger(RaidPlanner.class.getName());
	private final static int MAIN_MENU_OPTIONS = 6;
	private final static int MAIN_KEYBOARD_COLUMNS = 3;

	public String getBotToken() {
		return RaidPlanner.token;
	}

	public void onUpdateReceived(Update update) {

		if (update.hasMessage() && update.getMessage().hasText()) {

			Comms comm = new Comms(update);

			logger.info("Mensaje recibido de " + comm.getUsername() + ", ID: " + comm.getUserId());

			SendMessage registerReply;

			if ( "1".equals(update.getMessage().getText()) ) {

				logger.info("El usuario " + comm.getUsername() + ", ha enviado una petición de registro");

				if(register(update)) {
					registerReply = comm.message("Registrado correctamente");
				} else {
					registerReply = comm.message("Ya estás registrado");
				}

			} else {
				registerReply = comm.message("Selecione una opción:" + "\n\n" + "1. Registrarme");
				registerReply.setReplyMarkup(generateMainKeyboard());
			}

			try {
				execute(registerReply);
			} catch (TelegramApiException e) {
				logger.error(e.getMessage(), e);
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

	public boolean register(Update update) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("raidPlanner");
		MongoCollection<Document> users = database.getCollection("users");

		int userId = update.getMessage().getFrom().getId();

		long found = users.count(Document.parse("{_id : " + Integer.toString(userId) + "}"));

		if (found == 0) {
			User user = new User(userId
					, update.getMessage().getFrom().getUserName()
					, update.getMessage().getFrom().getFirstName()
					, update.getMessage().getFrom().getLastName()
					, update.getMessage().getFrom().getLanguageCode()
					);

			users.insertOne(user.toDocument());
			mongoClient.close();

			return true;
		}

		mongoClient.close();
		return false;
	}

	public ReplyKeyboardMarkup generateMainKeyboard() {

		ReplyKeyboardMarkup mainKeyboard = new ReplyKeyboardMarkup();
		mainKeyboard.setResizeKeyboard(true);

		List<KeyboardRow> kRows = new ArrayList<>();
		KeyboardRow kRow = new KeyboardRow();

		for (int i = 1; i <= MAIN_MENU_OPTIONS; i++) {
			KeyboardButton kButton = new KeyboardButton(String.valueOf(i));
			kRow.add(kButton);
			if ( (i % MAIN_KEYBOARD_COLUMNS) == 0) {
				kRows.add(kRow);
				kRow = new KeyboardRow();
			}
		}

		mainKeyboard.setKeyboard(kRows);

		return mainKeyboard;
	}

}
