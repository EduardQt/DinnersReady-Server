package se.hkr.freechoice.core.users;

import at.favre.lib.crypto.bcrypt.BCrypt;
import se.hkr.freechoice.data.BaseDao;
import se.hkr.freechoice.data.ConnectionProvider;

import java.nio.charset.StandardCharsets;

public class UserDao extends BaseDao<User> {
    public UserDao(ConnectionProvider connectionProvider) {
        super(User.class, connectionProvider);
    }

    public User authenticate(String username, String password) {
        User user = selectSingle("SELECT * FROM users WHERE username = ? LIMIT 1;", username);

        if (user != null) {
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if (result.verified) return user;
        }

        return null;
    }

    public User findById(String id) {
        return selectSingle("SELECT * FROM users WHERE id = ? LIMIT 1;", id);
    }

    public boolean createUser(String uuid, String username, String password) {
        byte[] bcryptHashBytes = BCrypt.withDefaults().hash(6, password.getBytes(StandardCharsets.UTF_8));
        return insert("INSERT INTO users(`id`, `username`, `password`) VALUES(?, ?, ?);", uuid, username, new String(bcryptHashBytes));
    }
}
