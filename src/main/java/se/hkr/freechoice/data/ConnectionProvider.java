package se.hkr.freechoice.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    public ConnectionProvider(String host, int port, String database, String username, String password) throws ClassNotFoundException {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        Class.forName("com.mysql.jdbc.Driver");
    }

    public Connection provideConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
    }

}
