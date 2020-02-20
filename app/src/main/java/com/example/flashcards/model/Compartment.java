package com.example.flashcards.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.flashcards.utils.NoDuplicateArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class Compartment {

    public static NoDuplicateArrayList<String> categories;
    public static HashMap<String, ArrayList<Flashcard>> knownFlashcards;
    public static HashMap<String, ArrayList<Flashcard>> unknownFlashcards;

    private static final String categoriesKey = "categories";
    private static final String knownFlashcardsKey = "knownFlashcards";
    private static final String unknownFlashcardsKey = "unknownFlashcards";

    public static void load(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String categoriesJson = sharedPreferences.getString(categoriesKey, null);
        // System.out.println(categoriesJson);
        if (categoriesJson != null) {
            categories = gson.fromJson(categoriesJson, new TypeToken<NoDuplicateArrayList<String>>(){}.getType());
        } else {
            categories = new NoDuplicateArrayList<>();
        }

        String knownFlashcardsJson = sharedPreferences.getString(knownFlashcardsKey, null);
        // System.out.println(knownFlashcardsJson);
        if (knownFlashcardsJson != null) {
            knownFlashcards = gson.fromJson(knownFlashcardsJson, new TypeToken<HashMap<String, ArrayList<Flashcard>>>(){}.getType());
        } else {
            knownFlashcards = new HashMap<>();
        }

        String unknownFlashcardsJson = sharedPreferences.getString(unknownFlashcardsKey, null);
        // System.out.println(unknownFlashcardsJson);
        if (unknownFlashcardsJson != null) {
            unknownFlashcards = gson.fromJson(unknownFlashcardsJson, new TypeToken<HashMap<String, ArrayList<Flashcard>>>(){}.getType());
        } else {
            unknownFlashcards = new HashMap<>();
        }
    }

    public static void save(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String categoriesJson = gson.toJson(categories);
        editor.putString(categoriesKey, categoriesJson);
        // System.out.println(categoriesJson);

        String knownFlashcardsJson = gson.toJson(knownFlashcards);
        editor.putString(knownFlashcardsKey, knownFlashcardsJson);
        // System.out.println(knownFlashcardsJson);

        String unknownFlashcardsJson = gson.toJson(unknownFlashcards);
        editor.putString(unknownFlashcardsKey, unknownFlashcardsJson);
        // System.out.println(unknownFlashcardsJson);

        editor.apply();
    }
}
