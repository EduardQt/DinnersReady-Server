package se.hkr.freechoice.models;

import java.util.Map;

public class FailResult extends BaseResult {
    public FailResult(Map<String, String> content) {
        super("fail", content);
    }
}
