package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.flashcards.database.Data;
import com.example.flashcards.dialogs.AddCategoryDialog;
import com.example.flashcards.dialogs.AddFlashcardDialog;
import com.example.flashcards.model.Level;
import com.example.flashcards.model.Flashcard;
import com.example.flashcards.R;

public class MainActivity extends AppCompatActivity implements
        AddFlashcardDialog.AddFlashcardDialogListener {

    private Button buttonAddFlashcard;
    private Button buttonKnownFlashcards;
    private Button buttonUnknownFlashcards;

    private AddFlashcardDialog addFlashcardDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data.init(this);
        Data.load();

        buttonAddFlashcard = findViewById(R.id.button_add_flashcard);
        buttonAddFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddFlashcardDialog();
            }
        });

        buttonKnownFlashcards = findViewById(R.id.button_known_flashcards);
        buttonKnownFlashcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryListActivity(Level.KNOWN);
            }
        });

        buttonUnknownFlashcards = findViewById(R.id.button_unknown_flashcards);
        buttonUnknownFlashcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryListActivity(Level.UNKNOWN);
            }
        });
    }

    private void openAddFlashcardDialog() {
        addFlashcardDialog = new AddFlashcardDialog(this, Data.categories.getArrayList());
        addFlashcardDialog.show(getSupportFragmentManager(), "Adding flashcard");
    }

    private void openCategoryListActivity(Level type) {
        Data.chosenLevel = type;
        Intent intent = new Intent(this, CategoryListActivity.class);
        startActivity(intent);
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        Data.addFlashcard(flashcard);
        Toast.makeText(this, "Udało się dodać fiszkę!", Toast.LENGTH_SHORT).show();
    }
}
