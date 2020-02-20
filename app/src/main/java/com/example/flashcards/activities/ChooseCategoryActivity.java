package com.example.flashcards.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashcards.R;
import com.example.flashcards.adapters.CategoryAdapter;
import com.example.flashcards.model.Compartment;
import com.example.flashcards.model.CompartmentType;
import com.example.flashcards.model.Flashcard;

import java.util.ArrayList;
import java.util.HashMap;

public class ChooseCategoryActivity extends AppCompatActivity {

    private ListView listViewCategories;
    private CategoryAdapter categoryAdapter;

    private HashMap<String, ArrayList<Flashcard>> flashcards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        chooseFlashcards();

        listViewCategories = findViewById(R.id.list_view_categories);
        categoryAdapter = new CategoryAdapter(this, R.layout.list_view_choose_category, Compartment.categories);
        listViewCategories.setAdapter(categoryAdapter);
    }

    private void chooseFlashcards() {
        Intent intent = getIntent();
        CompartmentType type = (CompartmentType) intent.getSerializableExtra("type");
        if (type == CompartmentType.KNOWN) {
            flashcards = Compartment.knownFlashcards;
        }
        else {
            flashcards = Compartment.unknownFlashcards;
        }
    }
}
