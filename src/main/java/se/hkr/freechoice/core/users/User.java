package se.hkr.freechoice.core.users;

import se.hkr.freechoice.data.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Model {

    private String id;
    private String username;
    private String password;
    private String key;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void serialize(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getString("id");
        this.username = resultSet.getString("username");
        this.password = resultSet.getString("password");
        this.key = resultSet.getString("key");
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}