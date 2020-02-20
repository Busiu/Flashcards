package com.example.flashcards;

public class Flashcard {

    private String category;
    private String englishPhrase;
    private String polishPhrase;

    public Flashcard(String category, String englishPhrase, String polishPhrase) {
        this.category = category;
        this.englishPhrase = englishPhrase;
        this.polishPhrase = polishPhrase;
    }

    public String getCategory() {
        return category;
    }

    public String getEnglishPhrase() {
        return englishPhrase;
    }

    public String getPolishPhrase() {
        return polishPhrase;
    }
}
