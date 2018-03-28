import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {

	private final static Logger logger = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args) {

		logger.info("Iniciando bot...");

		ApiContextInitializer.init();

		TelegramBotsApi botsApi = new TelegramBotsApi();

		try {
			botsApi.registerBot(new RaidPlanner());
		} catch (TelegramApiException e) {
			logger.error(e.getMessage(), e);
		}

		logger.info("Bot iniciado correctamente");

	}

}
