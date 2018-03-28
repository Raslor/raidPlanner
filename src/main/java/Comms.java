import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public class Comms {

	public static final String PARSE_MARKDOWN = "Markdown";
	public static final String PARSE_HTML = "Html";

	private long chatId;
	private int userId;
	private String username;
	private String reply;

	public Comms(Update update) {
		this.chatId = update.getMessage().getChatId();
		this.username = update.getMessage().getChat().getUserName();
		this.userId = update.getMessage().getFrom().getId();
	}

	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public SendMessage saludo() {

		String saludoES = "¡Hola! [";
		// String saludoEN = "Hello! [";

		SendMessage saludo = new SendMessage();

		saludo.setParseMode(PARSE_MARKDOWN);
		saludo.setChatId(chatId);
		saludo.setText(saludoES + username + "](tg://user?id=" + userId + ")");

		return saludo;
	}

	public SendMessage message(String text) {
		SendMessage message = new SendMessage();

		message.setChatId(chatId);
		message.setText(text);

		return message;
	}

	public SendMessage selectorMessage(String text) {
		SendMessage message = new SendMessage();

		

		return message;
	}

}