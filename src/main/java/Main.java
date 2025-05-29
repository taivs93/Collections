import entity.Selection;
import manager.DictionaryApp;

import java.util.Scanner;

public class Main {
    private static DictionaryApp dictionaryApp = new DictionaryApp();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean isInUsed = true;
        int choice;
        while (isInUsed){
            menu();
            choice = scanner.nextInt();
            Selection selection = Selection.getSelectionByChoice(choice);
            switch (selection){
                case ADD -> {
                    scanner.nextLine();
                    System.out.print("Word: ");
                    String word = inputWord();
                    System.out.print("Meaning: ");
                    String meaning = inputWord();
                    dictionaryApp.addWord(word,meaning);
                }
                case SEARCH -> {
                    scanner.nextLine();
                    System.out.print("Word: ");
                    String word = inputWord();
                    dictionaryApp.search(word);
                }
                case SEARCHBYPREFIX -> {
                    scanner.nextLine();
                    System.out.print("Prefix: ");
                    String prefix = inputWord();
                    dictionaryApp.searchWordByPrefix(prefix);
                }
                case VIEWLASTFIVEWORDS -> {
                    dictionaryApp.viewLastFiveSearchWords();
                }
                case VIEWSYNONYMS -> {
                    scanner.nextLine();
                    System.out.print("Word: ");
                    String word = inputWord();
                    dictionaryApp.viewSynonyms(word);
                }
                case EXIT -> {
                    isInUsed = false;
                }
            }
        }
    }
    private static void menu(){
        System.out.println("Dictionary App");
        for (Selection selection : Selection.values()){
            System.out.println(selection.getValue() + ": "+selection.getDescription());
        }
        System.out.print("Selection: ");
    }
    private static String inputWord(){
        return scanner.nextLine();
    }
}

