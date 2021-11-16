package se.hkr.freechoice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.hkr.freechoice.core.users.User;
import se.hkr.freechoice.core.users.UserComponent;
import se.hkr.freechoice.models.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
public class AuthController {

    @GetMapping("/login")
    public BaseResult login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            HttpServletResponse response
    ) {
        UserComponent userComponent = UserComponent.getInstance();
        String id = userComponent.authenticateUser(username, password);
        if (id != null) {
            response.setHeader("Status", "success");
            return new SuccessResult(new LoginData(id));
        } else {
            response.setHeader("Status", "error");
            return new FailResult(new HashMap<String, String>() {{
                put("error", "Invalid username or password");
            }});
        }
    }

    @PostMapping("/register")
    public BaseResult register(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            HttpServletResponse response
    ) {
        UserComponent userComponent = UserComponent.getInstance();
        String id = userComponent.createUser(username, password);
        if (id != null) {
            response.setHeader("Status", "success");
            return new SuccessResult(new RegisterData(id));
        } else {
            response.setHeader("Status", "error");
            return new FailResult(new HashMap<String, String>() {{
                put("error", "Username already exists");
            }});
        }
    }

    @PostMapping("/updatekey")
    public BaseResult updateKey(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "key") String key,
            HttpServletResponse response
    ) {
        UserComponent userComponent = UserComponent.getInstance();
        User user = userComponent.getUser(userId);
        if (user != null) {
            user.setKey(key);
            response.setHeader("Status", "success");
            return new SuccessResult(null);
        } else {
            response.setHeader("Status", "error");
            return new FailResult(new HashMap<String, String>() {{
                put("error", "Could not find user id");
            }});
        }
    }

}