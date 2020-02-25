package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.flashcards.database.Data;
import com.example.flashcards.model.Level;
import com.example.flashcards.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonKnownFlashcards;
    private Button buttonUnknownFlashcards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data.init(this);
        Data.load();

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

    private void openCategoryListActivity(Level type) {
        Data.chosenLevel = type;
        Intent intent = new Intent(this, CategoryListActivity.class);
        startActivity(intent);
    }
}
