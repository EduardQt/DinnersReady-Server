package se.hkr.freechoice.models;

public class RegisterData {

    private final String userId;

    public RegisterData(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
