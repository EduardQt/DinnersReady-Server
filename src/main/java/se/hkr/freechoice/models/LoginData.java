package se.hkr.freechoice.models;

public class LoginData {

    private final String userId;

    public LoginData(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
