package manager;

import entity.Word;

import java.util.*;

public class DictionaryApp {
    private Map<String, Word> dictionary = new HashMap<String, Word>();
    private List<String> lastFiveSearchWords = new LinkedList<String>();
    public void addWord(String word, String meaning){
        if(!dictionary.containsKey(word)){
            Word newWord = new Word();
            newWord.addMeaning(meaning);
            dictionary.put(word,newWord);
        }
        else {
            Word existedWord = dictionary.get(word);
            if (!existedWord.getMeaning().contains(meaning)){
                existedWord.addMeaning(meaning);
            }
        }
        for(String wordInDic : dictionary.keySet()){
            if(!wordInDic.equals(word) && dictionary.get(wordInDic).getMeaning().contains(meaning)){
                addSynonym(wordInDic,word);
                addSynonym(word,wordInDic);
            }
        }
        System.out.println("Add meaning of word successfully");
    }
    private void addSynonym(String word, String synonym){
        if(!dictionary.containsKey(word)){
            System.out.println("Word not found");
            suggestWord(word);
            return;
        }
        Word existedWord = dictionary.get(word);
        if(existedWord.getSynonyms().contains(synonym)){
            System.out.println("Synonyms existed");
            return;
        }
        existedWord.addSynonyms(synonym);
    }
    public void search(String word){
        if (!dictionary.containsKey(word)) {
            System.out.println("Word not found");
            this.suggestWord(word);
            return;
        }
        dictionary.get(word).getMeaning().forEach(meaning -> {
            System.out.println(word + ": " + meaning);
        });
        addToLastFiveSearchWords(word);
    }

    public void searchWordByPrefix(String prefix){
        System.out.println("Words start with "+prefix +":");
        Set<String> words = dictionary.keySet();
        words.stream().filter(word -> word.startsWith(prefix)).forEach(System.out::println);
    }
    public void viewLastFiveSearchWords(){
        lastFiveSearchWords.forEach(System.out::println);
    }
    public void viewSynonyms(String word){
        if (!dictionary.containsKey(word)) {
            System.out.println("Word not found");
            this.suggestWord(word);
            return;
        }
        System.out.println("Synonyms: ");
        dictionary.get(word).getSynonyms().forEach(System.out::println);
        addToLastFiveSearchWords(word);
    }
    private void addToLastFiveSearchWords(String word){
        if (this.lastFiveSearchWords.size() == 5) {
            this.lastFiveSearchWords.remove(0);
        }
        this.lastFiveSearchWords.add(word);
    }

    public int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j],
                            Math.min(
                                    dp[i][j - 1],
                                    dp[i - 1][j - 1]
                            )
                    );
                }
            }
        }

        return dp[a.length()][b.length()];
    }
    private void suggestWord(String word){
        System.out.println("Similar words:");
        dictionary.keySet().stream().filter(wordInDic -> levenshteinDistance(word,wordInDic) <= 2).forEach(System.out::println);
    }


}
