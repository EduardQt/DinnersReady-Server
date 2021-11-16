package se.hkr.freechoice.models;

public class BaseResult {

    private final String header;
    private final Object content;

    public BaseResult(String header, Object content) {
        this.header = header;
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public Object getContent() {
        return content;
    }
}
