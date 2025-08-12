import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDrivenChatbotNotifier {
    private Map<String, List<String>> subscriptions;
    private Map<String, String> notifications;

    public DataDrivenChatbotNotifier() {
        this.subscriptions = new HashMap<>();
        this.notifications = new HashMap<>();
    }

    public void subscribe(String userId, String topic) {
        if (!subscriptions.containsKey(userId)) {
            subscriptions.put(userId, new ArrayList<>());
        }
        subscriptions.get(userId).add(topic);
    }

    public void notify(String topic, String message) {
        for (Map.Entry<String, List<String>> entry : subscriptions.entrySet()) {
            if (entry.getValue().contains(topic)) {
                notifications.put(entry.getKey(), message);
            }
        }
    }

    public void displayNotifications(String userId) {
        if (notifications.containsKey(userId)) {
            System.out.println("Notifications for user " + userId + ":");
            System.out.println(notifications.get(userId));
        } else {
            System.out.println("No notifications for user " + userId);
        }
    }

    public static void main(String[] args) {
        DataDrivenChatbotNotifier notifier = new DataDrivenChatbotNotifier();
        notifier.subscribe("user1", "topicA");
        notifier.subscribe("user1", "topicB");
        notifier.subscribe("user2", "topicA");
        notifier.notify("topicA", "New update available!");
        notifier.notify("topicB", "New feature released!");
        notifier.displayNotifications("user1");
        notifier.displayNotifications("user2");
    }
}