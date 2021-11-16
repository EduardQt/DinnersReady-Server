package se.hkr.freechoice.core.users;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import java.util.*;

public class UserComponent {

    private final Map<String, User> users;
    private UserDao userDao;

    public UserComponent() {
        this.users = new HashMap<>();
    }

    public User getUser(String id) {
        return this.userDao.findById(id);
    }

    public String authenticateUser(String username, String password) {
        User user = this.userDao.authenticate(username, password);
        if (user != null) return user.getId();

        return null;
    }

    public String createUser(String username, String password) {
        String uuid = UUID.randomUUID().toString();
        boolean result = this.userDao.createUser(uuid, username, password);
        if (result) return uuid;

        return null;
    }

    public int sendMessage(String title, String body) throws FirebaseMessagingException {
        List<Message> messages = new ArrayList<>();
        for (User user : this.users.values()) {
            if (user.getKey() == null) continue;

            messages.add(Message.builder()
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .setToken(user.getKey())
                    .build());
        }

        return FirebaseMessaging.getInstance().sendAll(messages).getSuccessCount();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    static {
        instance = new UserComponent();
    }

    private static UserComponent instance;

    public static UserComponent getInstance() {
        return instance;
    }
}
