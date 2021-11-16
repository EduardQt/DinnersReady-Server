package se.hkr.freechoice.controllers;

import org.springframework.web.bind.annotation.*;
import se.hkr.freechoice.models.BaseResult;
import se.hkr.freechoice.models.SuccessResult;

@RestController
public class HouseHoldController {

    @GetMapping("/household/get")
    public BaseResult houseHolds(
            @RequestHeader(name = "Auth-Code") int userId
    ) {
        System.out.println(userId);
        return new SuccessResult("test");
    }

    @PostMapping("/household/join")
    public BaseResult join(
            @RequestParam(name = "household") int houseHoldId,
            @RequestHeader(name = "Auth-Code") int userId
    ) {
        System.out.println(houseHoldId);
        System.out.println(userId);
        return new SuccessResult("test");
    }

}
