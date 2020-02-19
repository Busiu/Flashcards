package com.example.flashcards;

public class Flashcard {

    private String category;
    private String englishVersion;
    private String polishVersion;

    public Flashcard(String category, String englishVersion, String polishVersion) {
        this.category = category;
        this.englishVersion = englishVersion;
        this.polishVersion = polishVersion;
    }

    public String getCategory() {
        return category;
    }

    public String getEnglishVersion() {
        return englishVersion;
    }

    public String getPolishVersion() {
        return polishVersion;
    }
}
