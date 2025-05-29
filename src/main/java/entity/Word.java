package entity;

import java.util.ArrayList;
import java.util.List;
public class Word {
    private List<String> meanings = new ArrayList<String>();
    private List<String> synonyms = new ArrayList<String>();
    public List<String> getMeaning() {
        return meanings;
    }

    public void addMeaning(String meaning) {
        this.meanings.add(meaning);
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void addSynonyms(String synonyms) {
        this.synonyms.add(synonyms);
    }
}
