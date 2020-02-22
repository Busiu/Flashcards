package com.example.flashcards.model;

public class Flashcard {

    private Level level;
    private Phrase englishPhrase;
    private Phrase polishPhrase;
    private String category;

    public Flashcard(Phrase englishPhrase, Phrase polishPhrase, String category) {
        this.level = Level.UNKNOWN;
        this.category = category;
        this.englishPhrase = englishPhrase;
        this.polishPhrase = polishPhrase;
    }

    public Flashcard(Level level, Phrase englishPhrase, Phrase polishPhrase, String category) {
        this.level = level;
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

    public Level getLevel() {
        return level;
    }

    public Phrase getPolishPhrase() {
        return polishPhrase;
    }
}
