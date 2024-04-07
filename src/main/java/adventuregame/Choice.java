package adventuregame;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Choice implements Serializable {
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

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Choice choice = (Choice) obj;;
        return choice.getChoiceLink() == Choice_Link && choice.getDescription().equals(Choice_Description);
    }
}
