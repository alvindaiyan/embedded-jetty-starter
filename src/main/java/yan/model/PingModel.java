package yan.model;

/**
 * Created by yan on 22/04/2016.
 */
public class PingModel {
    private String name;
    private String text;

    public PingModel(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
