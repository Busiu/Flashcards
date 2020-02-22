package com.example.flashcards.database;

import android.provider.BaseColumns;

public class FlashcardsContract {

    private FlashcardsContract() {}

    public static final class FlashcardsEntry implements BaseColumns {
        public static final String TABLE_NAME = "flashcardTable";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_LEVEL = "level";
        public static final String COLUMN_ENGLISH_PHRASE = "englishPhrase";
        public static final String COLUMN_POLISH_PHRASE = "polishPhrase";
    }

    public static final class CategoriesEntry implements BaseColumns {
        public static final String TABLE_NAME = "categoriesTable";
        public static final String COLUMN_NAME = "name";
    }
}
