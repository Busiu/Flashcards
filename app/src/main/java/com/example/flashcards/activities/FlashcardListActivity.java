package com.example.flashcards.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashcards.R;
import com.example.flashcards.adapters.FlashcardAdapter;
import com.example.flashcards.database.Data;
import com.example.flashcards.dialogs.AddFlashcardDialog;
import com.example.flashcards.dialogs.SimpleDeleteDialog;
import com.example.flashcards.model.Level;
import com.example.flashcards.model.Flashcard;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FlashcardListActivity extends AppCompatActivity implements
        SimpleDeleteDialog.SimpleDeleteDialogListener,
        AddFlashcardDialog.AddFlashcardDialogListener {

    private FloatingActionButton fabAddFlashcard;
    private ListView listViewFlashcards;
    private FlashcardAdapter flashcardAdapter;

    private AddFlashcardDialog addFlashcardDialog;
    private SimpleDeleteDialog simpleDeleteDialog;

    private ArrayList<Flashcard> flashcards;

    private String chosenCategory;
    private Level chosenLevel;

    private static final String deleteDialogTitle = "Usuwanie fiszki:";
    private static final String deleteDialogInfo = "Czy na pewno chcesz usunąć tę fiszkę?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        chosenCategory = Data.chosenCategory;
        chosenLevel = Data.chosenLevel;
        chooseFlashcards();

        fabAddFlashcard = findViewById(R.id.fab_add_object);
        fabAddFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddFlashcardDialog();
            }
        });

        listViewFlashcards = findViewById(R.id.list_view_object);
        flashcardAdapter = new FlashcardAdapter(
                this,
                R.layout.list_view_flashcard,
                flashcards,
                this);
        listViewFlashcards.setAdapter(flashcardAdapter);
    }

    private void openAddFlashcardDialog() {
        addFlashcardDialog = new AddFlashcardDialog(this, Data.categories.getArrayList());
        addFlashcardDialog.show(getSupportFragmentManager(), "Adding flashcard");
    }

    private void chooseFlashcards() {
        if (chosenLevel == Level.KNOWN) {
            flashcards = Data.knownFlashcards.get(chosenCategory);
        } else {
            flashcards = Data.unknownFlashcards.get(chosenCategory);
        }
    }

    public void openSimpleDeleteDialog(Flashcard flashcard) {
        simpleDeleteDialog = new SimpleDeleteDialog(this, flashcard, deleteDialogTitle, deleteDialogInfo);
        simpleDeleteDialog.show(getSupportFragmentManager(), "Open Simple Delete Dialog");
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        Data.addFlashcard(flashcard);
        Toast.makeText(this, "Udało się dodać fiszkę!", Toast.LENGTH_SHORT).show();
        flashcardAdapter.notifyDataSetChanged();
    }

    @Override
    public void simpleDelete(Object object) {
        Data.removeFlashcard((Flashcard) object);
        flashcardAdapter.notifyDataSetChanged();
    }
}
