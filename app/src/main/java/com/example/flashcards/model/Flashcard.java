package com.example.flashcards.model;

public class Flashcard {

    private Phrase englishPhrase;
    private Phrase polishPhrase;
    private String category;

    public Flashcard(Phrase englishPhrase, Phrase polishPhrase, String category) {
        this.category = category;
        this.englishPhrase = englishPhrase;
        this.polishPhrase = polishPhrase;
    }

    public String getCategory() {
        return category;
    }

    public Phrase getEnglishPhrase() {
        return englishPhrase;
    }

    public Phrase getPolishPhrase() {
        return polishPhrase;
    }
}
