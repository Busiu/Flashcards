package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.flashcards.dialogs.AddCategoryDialog;
import com.example.flashcards.dialogs.AddFlashcardDialog;
import com.example.flashcards.model.Compartment;
import com.example.flashcards.model.CompartmentType;
import com.example.flashcards.model.Flashcard;
import com.example.flashcards.utils.NoDuplicateArrayList;
import com.example.flashcards.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        AddCategoryDialog.AddCategoryDialogListener,
        AddFlashcardDialog.AddFlashcardDialogListener {

    private Button buttonAddCategory;
    private Button buttonAddFlashcard;
    private Button buttonKnownFlashcards;
    private Button buttonUnknownFlashcards;

    private AddCategoryDialog addCategoryDialog;
    private AddFlashcardDialog addFlashcardDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Compartment.init();

        buttonAddCategory = findViewById(R.id.button_add_category);
        buttonAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCategoryDialog();
            }
        });

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
                openChooseCategoryActivity(CompartmentType.KNOWN);
            }
        });

        buttonUnknownFlashcards = findViewById(R.id.button_unknown_flashcards);
        buttonUnknownFlashcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChooseCategoryActivity(CompartmentType.UNKNOWN);
            }
        });

    }

    private void openAddCategoryDialog() {
        addCategoryDialog = new AddCategoryDialog(this);
        addCategoryDialog.show(getSupportFragmentManager(), "Adding category");
    }

    private void openAddFlashcardDialog() {
        addFlashcardDialog = new AddFlashcardDialog(this, Compartment.categories.getArrayList());
        addFlashcardDialog.show(getSupportFragmentManager(), "Adding flashcard");
    }

    private void openChooseCategoryActivity(CompartmentType type) {
        Intent intent = new Intent(this, ChooseCategoryActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    @Override
    public void addCategory(String category) {
        boolean result = Compartment.categories.add(category);
        if (result) {
            Compartment.knownFlashcards.put(category, new ArrayList<Flashcard>());
            Compartment.unknownFlashcards.put(category, new ArrayList<Flashcard>());
            Toast.makeText(this, "Udało się dodać kategorię!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Taka kategoria już isnieje!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        Compartment.unknownFlashcards.get(flashcard.getCategory()).add(flashcard);
        Toast.makeText(this, "Udało się dodać fiszkę!", Toast.LENGTH_SHORT).show();
    }
}
