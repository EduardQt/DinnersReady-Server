package se.hkr.freechoice.controllers;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.hkr.freechoice.core.users.UserComponent;
import se.hkr.freechoice.models.BaseResult;
import se.hkr.freechoice.models.SuccessResult;

import javax.servlet.http.HttpServletResponse;

@RestController
public class NotificationController {

    @PostMapping("/createNotification")
    public BaseResult createNotification(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body,
            HttpServletResponse response
    ) throws FirebaseMessagingException {
        System.out.println(title);
        System.out.println(body);

        response.setHeader("Status", "success");
        UserComponent.getInstance().sendMessage(title, body);
        return new SuccessResult(null);
    }
}