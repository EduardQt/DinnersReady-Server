package se.hkr.freechoice.core.household;

public class HouseHold {
    private final int id;
    private final String houseHoldName;

    public HouseHold(int id, String houseHoldName) {
        this.id = id;
        this.houseHoldName = houseHoldName;
    }

    public int getId() {
        return id;
    }

    public String getHouseHoldName() {
        return houseHoldName;
    }
}
