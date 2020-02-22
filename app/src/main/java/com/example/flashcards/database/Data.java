package com.example.flashcards.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.flashcards.model.Flashcard;
import com.example.flashcards.model.FlashcardGameType;
import com.example.flashcards.model.Language;
import com.example.flashcards.model.Level;
import com.example.flashcards.model.Phrase;
import com.example.flashcards.utils.NoDuplicateArrayList;
import com.example.flashcards.database.FlashcardsContract.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {

    private static SQLiteDatabase database;

    public static NoDuplicateArrayList<String> categories;
    public static HashMap<String, ArrayList<Flashcard>> knownFlashcards;
    public static HashMap<String, ArrayList<Flashcard>> unknownFlashcards;

    public static Level chosenLevel = null;
    public static String chosenCategory = null;
    public static FlashcardGameType chosenFlashcardGameType = null;

    private Data() {}

    public static boolean addCategory(String category) {
        boolean result = categories.add(category);
        if (result) {
            ContentValues cv = new ContentValues();
            cv.put(CategoriesEntry.COLUMN_NAME, category);
            database.insert(CategoriesEntry.TABLE_NAME, null, cv);

            knownFlashcards.put(category, new ArrayList<Flashcard>());
            unknownFlashcards.put(category, new ArrayList<Flashcard>());
        }

        return result;
    }

    public static void addFlashcard(Flashcard flashcard) {
        ContentValues cv = new ContentValues();
        cv.put(FlashcardsEntry.COLUMN_CATEGORY, flashcard.getCategory());
        cv.put(FlashcardsEntry.COLUMN_LEVEL, flashcard.getLevel().toString());
        cv.put(FlashcardsEntry.COLUMN_ENGLISH_PHRASE, flashcard.getEnglishPhrase().toString());
        cv.put(FlashcardsEntry.COLUMN_POLISH_PHRASE, flashcard.getPolishPhrase().toString());

        database.insert(FlashcardsEntry.TABLE_NAME, null, cv);
        if (flashcard.getLevel() == Level.KNOWN) {
            knownFlashcards.get(flashcard.getCategory()).add(flashcard);
        } else {
            unknownFlashcards.get(flashcard.getCategory()).add(flashcard);
        }
    }

    public static void init(Context context) {
        initDatabase(context);
        initCollections();
    }

    public static void load() {
        loadCategories();
        loadFlashcards();
    }

    public static void removeCategory(String category) {
        deleteCategory(category);
        deleteFlashcardsOfCategory(category);
    }

    public static void removeFlashcard(Flashcard flashcard) {
        String whereClause = FlashcardsEntry.COLUMN_ENGLISH_PHRASE + "=\"" +
                flashcard.getEnglishPhrase() + "\" AND " +
                FlashcardsEntry.COLUMN_POLISH_PHRASE + "=\"" +
                flashcard.getPolishPhrase() + "\"";

        database.delete(FlashcardsEntry.TABLE_NAME, whereClause, null);

        if(flashcard.getLevel() == Level.KNOWN) {
            knownFlashcards.get(flashcard.getCategory()).remove(flashcard);
        } else {
            unknownFlashcards.get(flashcard.getCategory()).remove(flashcard);
        }
    }

    public static void switchLevelOfFlashcardToDatabase(Flashcard flashcard) {
        String whereClause = FlashcardsEntry.COLUMN_ENGLISH_PHRASE + "=\"" +
                flashcard.getEnglishPhrase() + "\" AND " +
                FlashcardsEntry.COLUMN_POLISH_PHRASE + "=\"" +
                flashcard.getPolishPhrase() + "\"";

        ContentValues cv = new ContentValues();
        cv.put(FlashcardsEntry.COLUMN_CATEGORY, flashcard.getCategory());
        cv.put(FlashcardsEntry.COLUMN_LEVEL, flashcard.getLevel().switchLevel().toString());
        cv.put(FlashcardsEntry.COLUMN_ENGLISH_PHRASE, flashcard.getEnglishPhrase().toString());
        cv.put(FlashcardsEntry.COLUMN_POLISH_PHRASE, flashcard.getPolishPhrase().toString());

        database.update(FlashcardsEntry.TABLE_NAME, cv, whereClause, null);
    }

    private static void deleteCategory(String category) {
        String whereClause = CategoriesEntry.COLUMN_NAME + "=\"" +
                category + "\"";

        database.delete(CategoriesEntry.TABLE_NAME, whereClause, null);
        categories.remove(category);
    }

    private static void deleteFlashcardsOfCategory(String category) {
        String whereClause = FlashcardsEntry.COLUMN_CATEGORY + "=\"" +
                category + "\"";

        database.delete(FlashcardsEntry.TABLE_NAME, whereClause, null);
        knownFlashcards.remove(category);
        unknownFlashcards.remove(category);
    }

    private static void initDatabase(Context context) {
        FlashcardsDBHelper helper = new FlashcardsDBHelper(context);
        database = helper.getWritableDatabase();
    }

    private static void initCollections() {
        categories = new NoDuplicateArrayList<>();
        knownFlashcards = new HashMap<>();
        unknownFlashcards = new HashMap<>();
    }

    private static void loadCategories() {
        Cursor cursor = database.rawQuery("SELECT * FROM " + CategoriesEntry.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String category = cursor.getString(cursor.getColumnIndex(CategoriesEntry.COLUMN_NAME));
                categories.add(category);

                knownFlashcards.put(category, new ArrayList<Flashcard>());
                unknownFlashcards.put(category, new ArrayList<Flashcard>());

                cursor.moveToNext();
            }
        }

        cursor.close();
    }

    private static void loadFlashcards() {
        Cursor cursor = database.rawQuery("SELECT * FROM " + FlashcardsEntry.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String categoryString = cursor.getString(cursor.getColumnIndex(FlashcardsEntry.COLUMN_CATEGORY));
                String levelString = cursor.getString(cursor.getColumnIndex(FlashcardsEntry.COLUMN_LEVEL));
                String englishPhraseString = cursor.getString(cursor.getColumnIndex(FlashcardsEntry.COLUMN_ENGLISH_PHRASE));
                String polishPhraseString = cursor.getString(cursor.getColumnIndex(FlashcardsEntry.COLUMN_POLISH_PHRASE));

                Level level = Level.stringToEnum(levelString);
                Phrase englishPhrase = new Phrase(Language.ENGLISH, englishPhraseString);
                Phrase polishPhrase = new Phrase(Language.POLISH, polishPhraseString);

                if(level == Level.KNOWN) {
                    knownFlashcards.get(categoryString).add(new Flashcard(level, englishPhrase, polishPhrase, categoryString));
                } else {
                    unknownFlashcards.get(categoryString).add(new Flashcard(level, englishPhrase, polishPhrase, categoryString));
                }

                cursor.moveToNext();
            }
        }

        cursor.close();
    }
}
