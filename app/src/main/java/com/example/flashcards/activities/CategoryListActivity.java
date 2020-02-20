package com.example.flashcards.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashcards.R;
import com.example.flashcards.adapters.CategoryAdapter;
import com.example.flashcards.dialogs.CategoryOptionsDialog;
import com.example.flashcards.model.Compartment;
import com.example.flashcards.model.CompartmentType;
import com.example.flashcards.model.Flashcard;
import com.example.flashcards.utils.ChosenObjects;
import com.example.flashcards.utils.NoDuplicateArrayList;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryListActivity extends AppCompatActivity {

    private ListView listViewCategories;
    private CategoryAdapter categoryAdapter;

    private CategoryOptionsDialog categoryOptionsDialog;

    private HashMap<String, ArrayList<Flashcard>> flashcards;
    private NoDuplicateArrayList<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        categories = Compartment.categories;
        chooseFlashcards();

        listViewCategories = findViewById(R.id.list_view_categories);
        categoryAdapter = new CategoryAdapter(this, R.layout.list_view_choose_category, Compartment.categories);
        listViewCategories.setAdapter(categoryAdapter);
        listViewCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openCategoryOptionsDialog(position);
            }
        });
    }

    private void chooseFlashcards() {
        CompartmentType type = ChosenObjects.currentlyChosenCompartmentType;
        if (type == CompartmentType.KNOWN) {
            flashcards = Compartment.knownFlashcards;
        }
        else {
            flashcards = Compartment.unknownFlashcards;
        }
    }

    private void openCategoryOptionsDialog(int position) {
        ChosenObjects.currentlyChosenCategory = categories.get(position);
        categoryOptionsDialog = new CategoryOptionsDialog();
        categoryOptionsDialog.show(getSupportFragmentManager(), "Category List");
    }
}
