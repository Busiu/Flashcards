package com.example.flashcards.activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashcards.R;
import com.example.flashcards.adapters.FlashcardAdapter;
import com.example.flashcards.database.Data;
import com.example.flashcards.dialogs.SimpleDeleteDialog;
import com.example.flashcards.model.Level;
import com.example.flashcards.model.Flashcard;

import java.util.ArrayList;

public class FlashcardListActivity extends AppCompatActivity implements
        SimpleDeleteDialog.SimpleDeleteDialogListener {

    private ListView listViewFlashcards;
    private FlashcardAdapter flashcardAdapter;

    private SimpleDeleteDialog simpleDeleteDialog;

    private ArrayList<Flashcard> flashcards;

    private String chosenCategory;
    private Level chosenLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        chosenCategory = Data.chosenCategory;
        chosenLevel = Data.chosenLevel;
        chooseFlashcards();

        listViewFlashcards = findViewById(R.id.list_view_object);
        flashcardAdapter = new FlashcardAdapter(this, R.layout.list_view_flashcard, flashcards, this);
        listViewFlashcards.setAdapter(flashcardAdapter);
    }

    private void chooseFlashcards() {
        if (chosenLevel == Level.KNOWN) {
            flashcards = Data.knownFlashcards.get(chosenCategory);
        } else {
            flashcards = Data.unknownFlashcards.get(chosenCategory);
        }
    }

    public void openSimpleDeleteDialog(Flashcard flashcard) {
        simpleDeleteDialog = new SimpleDeleteDialog(this, flashcard);
        simpleDeleteDialog.show(getSupportFragmentManager(), "Open Simple Delete Dialog");
    }

    @Override
    public void simpleDelete(Object object) {
        Data.removeFlashcard((Flashcard) object);
        flashcardAdapter.notifyDataSetChanged();
    }
}
