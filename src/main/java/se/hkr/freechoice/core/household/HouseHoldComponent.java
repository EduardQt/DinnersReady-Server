package se.hkr.freechoice.core.household;

import java.util.HashMap;
import java.util.Map;

public class HouseHoldComponent {

    private final Map<Integer, HouseHold> houseHolds;

    public HouseHoldComponent() {
        this.houseHolds = new HashMap<>();
    }

    public HouseHold getHouseHold(int houseId) {
        return this.houseHolds.get(houseId);
    }

}
