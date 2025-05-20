package entity;

public enum Selection {
    SEARCH(1,"SEARCH WORD"),
    ADD(2,"ADD MEANING OF WORD TO THE DICTIONARY"),
    SEARCHBYPREFIX(3,"SEARCH WORD BY PREFIX"),
    VIEWLASTFIVEWORDS(4,"VIEW LAST FIVE WORDS"),
    VIEWSYNONYMS(5,"VIEW SYNONYMS"),
    EXIT(6,"EXIT");

    private int value;
    private String description;

    Selection(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static Selection getSelectionByChoice(int value){
        for (Selection selection : Selection.values()){
            if(selection.value == value) return selection;
        }
        throw new IllegalArgumentException("SELECTION NOT FOUND");
    }
}
