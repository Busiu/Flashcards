package com.example.flashcards.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashcards.R;
import com.example.flashcards.adapters.CategoryAdapter;
import com.example.flashcards.database.Data;
import com.example.flashcards.dialogs.CategoryOptionsDialog;
import com.example.flashcards.model.Level;
import com.example.flashcards.model.Flashcard;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryListActivity extends AppCompatActivity {

    private ListView listViewCategories;
    private CategoryAdapter categoryAdapter;

    private CategoryOptionsDialog categoryOptionsDialog;

    private HashMap<String, ArrayList<Flashcard>> flashcards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        chooseFlashcards();

        listViewCategories = findViewById(R.id.list_view_object);
        categoryAdapter = new CategoryAdapter(this, R.layout.list_view_category, Data.categories, flashcards);
        listViewCategories.setAdapter(categoryAdapter);
        listViewCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openCategoryOptionsDialog(position);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        categoryAdapter.notifyDataSetChanged();
    }

    private void chooseFlashcards() {
        Level type = Data.chosenLevel;
        if (type == Level.KNOWN) {
            flashcards = Data.knownFlashcards;
        }
        else {
            flashcards = Data.unknownFlashcards;
        }
    }

    private void openCategoryOptionsDialog(int position) {
        Data.chosenCategory = Data.categories.get(position);
        categoryOptionsDialog = new CategoryOptionsDialog();
        categoryOptionsDialog.show(getSupportFragmentManager(), "Category List");
    }
}
