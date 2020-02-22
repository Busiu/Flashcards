package com.example.flashcards.model;

public enum Level {

    KNOWN("KNOWN"),
    UNKNOWN("UNKNOWN");

    private String name;

    Level(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Level stringToEnum(String string) {
        if(KNOWN.name.equals(string)){
            return KNOWN;
        }
        return UNKNOWN;
    }

    public Level switchLevel() {
        if (this == KNOWN) {
            return UNKNOWN;
        }
        return KNOWN;
    }
}
