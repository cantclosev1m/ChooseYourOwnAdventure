package adventuregame;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Choice {
    @JsonProperty("Choice_Description")
    private String Choice_Description;

    @JsonProperty("Choice_link")
    private int Choice_Link;

    public String getDescription() {
        return Choice_Description;
    }

    public int getChoiceLink()
    {
        return Choice_Link;
    }
}
