package com.example.flashcards.activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashcards.R;
import com.example.flashcards.adapters.FlashcardAdapter;
import com.example.flashcards.dialogs.SimpleDeleteDialog;
import com.example.flashcards.model.Compartment;
import com.example.flashcards.model.CompartmentType;
import com.example.flashcards.model.Flashcard;
import com.example.flashcards.utils.ChosenObjects;

import java.util.ArrayList;

public class FlashcardListActivity extends AppCompatActivity implements
        SimpleDeleteDialog.SimpleDeleteDialogListener {

    private ListView listViewFlashcards;
    private FlashcardAdapter flashcardAdapter;

    private SimpleDeleteDialog simpleDeleteDialog;

    private ArrayList<Flashcard> flashcards;

    private String chosenCategory;
    private CompartmentType chosenCompartmentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        chosenCategory = ChosenObjects.currentlyChosenCategory;
        chosenCompartmentType = ChosenObjects.currentlyChosenCompartmentType;
        chooseFlashcards();

        listViewFlashcards = findViewById(R.id.list_view_object);
        flashcardAdapter = new FlashcardAdapter(this, R.layout.list_view_flashcard, flashcards, this);
        listViewFlashcards.setAdapter(flashcardAdapter);
    }

    private void chooseFlashcards() {
        if (chosenCompartmentType == CompartmentType.KNOWN) {
            flashcards = Compartment.knownFlashcards.get(chosenCategory);
        } else {
            flashcards = Compartment.unknownFlashcards.get(chosenCategory);
        }
    }

    public void openSimpleDeleteDialog(Flashcard flashcard) {
        simpleDeleteDialog = new SimpleDeleteDialog(this, flashcard);
        simpleDeleteDialog.show(getSupportFragmentManager(), "Open Simple Delete Dialog");
    }

    @Override
    public void simpleDelete(Object object) {
        flashcardAdapter.remove((Flashcard) object);
    }
}
