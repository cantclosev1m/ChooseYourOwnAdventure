package adventuregame;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Represents a choice in an adventure game.
 * This class provides methods to get choice details and check equality between two choices.
 */
public class Choice implements Serializable {

    /**
     * The description of the choice.
     */
    @JsonProperty("Choice_Description")
    private String Choice_Description;

    /**
     * The link associated with this choice, typically pointing to the next part of the adventure.
     */
    @JsonProperty("Choice_link")
    private int Choice_Link;

     /**
     * Retrieves the description of the choice.
     * 
     * @return the choice description.
     */
    public String getDescription() {
        return Choice_Description;
    }

    /**
     * Retrieves the link associated with this choice.
     * 
     * @return the choice link as an integer.
     */
    public int getChoiceLink() {
        return Choice_Link;
    }

    /**
     * Checks if this Choice is equal to another object.
     * Two choices are considered equal if they have the same link and description.
     *
     * @param obj the object to compare with this Choice
     * @return true if the specified object is a Choice with the same link and description, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Choice choice = (Choice) obj;
        return choice.getChoiceLink() == Choice_Link && choice.getDescription().equals(Choice_Description);
    }
}
