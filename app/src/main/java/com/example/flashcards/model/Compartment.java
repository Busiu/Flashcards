package com.example.flashcards.model;

import com.example.flashcards.utils.NoDuplicateArrayList;

import java.util.ArrayList;
import java.util.HashMap;

public class Compartment {

    public static NoDuplicateArrayList<String> categories;
    public static HashMap<String, ArrayList<Flashcard>> knownFlashcards;
    public static HashMap<String, ArrayList<Flashcard>> unknownFlashcards;

    public static void init() {
        categories = new NoDuplicateArrayList<>();
        knownFlashcards = new HashMap<>();
        unknownFlashcards = new HashMap<>();
    }
}
