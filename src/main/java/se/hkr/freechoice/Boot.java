package se.hkr.freechoice;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.hkr.freechoice.core.users.UserComponent;
import se.hkr.freechoice.core.users.UserDao;
import se.hkr.freechoice.data.ConnectionProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@SpringBootApplication
public class Boot {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String password = "spreed123";
        for (int i = 0; i < 5; i++) {
            byte[] bcryptHashBytes = BCrypt.withDefaults().hash(6, password.getBytes(StandardCharsets.UTF_8));
            BCrypt.Result result = BCrypt.verifyer().verify("haha123".toCharArray(), new String(bcryptHashBytes));
            System.out.println(result.verified);
            System.out.println(new String(bcryptHashBytes));
            System.out.println(new String(bcryptHashBytes).length());
            System.out.println(bcryptHashBytes.length);
        }
        setupDAOs();

        SpringApplication.run(Boot.class, args);

        FileInputStream serviceAccount =
                new FileInputStream("serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }

    private static void setupDAOs() throws ClassNotFoundException {
        ConnectionProvider connectionProvider = new ConnectionProvider("localhost", 3306, "dinnersready", "root", "");
        UserDao userDao = new UserDao(connectionProvider);
        UserComponent.getInstance().setUserDao(userDao);
    }

}
