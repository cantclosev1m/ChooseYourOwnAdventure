package adventuregame;

public class Choice {
    private String description;
    private int choiceLink;

    public Choice(String description, int choiceLink) {
        this.description = description;
        this.choiceLink = choiceLink;
    }

    public String getDescription() {
        return description;
    }

    public int getChoiceLink()
    {
        return choiceLink;
    }
}
