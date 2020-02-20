package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.flashcards.dialogs.AddCategoryDialog;
import com.example.flashcards.dialogs.AddFlashcardDialog;
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

    private ArrayList<Flashcard> unknownFlashcards = new ArrayList<>();
    private NoDuplicateArrayList<String> categories = new NoDuplicateArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

    private void openAddCategoryDialog() {
        addCategoryDialog = new AddCategoryDialog(this);
        addCategoryDialog.show(getSupportFragmentManager(), "Adding category");
    }

    private void openAddFlashcardDialog() {
        addFlashcardDialog = new AddFlashcardDialog(this, categories.getArrayList());
        addFlashcardDialog.show(getSupportFragmentManager(), "Adding flashcard");
    }

    @Override
    public void addCategory(String category) {
        boolean result = categories.add(category);
        if (result) {
            Toast.makeText(this, "Udało się dodać kategorię!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Taka kategoria już isnieje!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        unknownFlashcards.add(flashcard);
        Toast.makeText(this, "Udało się dodać fiszkę!", Toast.LENGTH_SHORT).show();
    }
}
