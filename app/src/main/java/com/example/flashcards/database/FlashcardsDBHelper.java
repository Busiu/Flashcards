package com.example.flashcards.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.flashcards.database.FlashcardsContract.*;

public class FlashcardsDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "flashcards.db";
    private static final int DATABASE_VERSION = 1;

    public FlashcardsDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createCategoriesTable(db);
        createFlashcardsTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FlashcardsEntry.TABLE_NAME);
        onCreate(db);
    }

    private void createCategoriesTable(SQLiteDatabase db) {
        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesEntry.TABLE_NAME + " (" +
                CategoriesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesEntry.COLUMN_NAME + " TEXT NOT NULL" +
                ");";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
    }

    private void createFlashcardsTable(SQLiteDatabase db) {
        final String SQL_CREATE_FLASHCARDS_TABLE = "CREATE TABLE " +
                FlashcardsEntry.TABLE_NAME + " (" +
                FlashcardsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FlashcardsEntry.COLUMN_CATEGORY + " TEXT NOT NULL, " +
                FlashcardsEntry.COLUMN_LEVEL + " TEXT NOT NULL, " +
                FlashcardsEntry.COLUMN_ENGLISH_PHRASE + " TEXT NOT NULL, " +
                FlashcardsEntry.COLUMN_POLISH_PHRASE + " TEXT NOT NULL" +
                ");";

        db.execSQL(SQL_CREATE_FLASHCARDS_TABLE);
    }
}
