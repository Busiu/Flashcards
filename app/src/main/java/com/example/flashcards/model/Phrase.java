package com.example.flashcards.model;

public class Phrase {

    private Language language;
    private String phrase;

    public Phrase(Language language, String phrase) {
        this.language = language;
        this.phrase = phrase;
    }

    public Language getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return phrase;
    }
}
