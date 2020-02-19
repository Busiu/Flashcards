package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        AddCategoryDialog.AddCategoryDialogListener {

    private Button buttonKnownFlashcards;
    private Button buttonUnknownFlashcards;
    private Button buttonAddFlashcard;
    private Button buttonAddCategory;

    private AddCategoryDialog addCategoryDialog;

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
    }

    private void openAddCategoryDialog() {
        addCategoryDialog = new AddCategoryDialog(this);
        addCategoryDialog.show(getSupportFragmentManager(), "Adding category");
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
}
