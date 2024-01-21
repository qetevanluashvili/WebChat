public class MessageValidator {
    private MessageValidator() {
    }
    private static MessageValidator instance;
    public static synchronized MessageValidator getInstance() {
        if (instance == null) {
            instance = new MessageValidator();
        }
        return instance;
    }


    public boolean isValidMessage(String message) {
        return !message.contains("\n");
    }
}
