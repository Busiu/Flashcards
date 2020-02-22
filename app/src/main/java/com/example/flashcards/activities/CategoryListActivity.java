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
import com.example.flashcards.dialogs.SimpleDeleteDialog;
import com.example.flashcards.model.Level;
import com.example.flashcards.model.Flashcard;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryListActivity extends AppCompatActivity implements
        SimpleDeleteDialog.SimpleDeleteDialogListener {

    private ListView listViewCategories;
    private CategoryAdapter categoryAdapter;

    private CategoryOptionsDialog categoryOptionsDialog;
    private SimpleDeleteDialog simpleDeleteDialog;

    private HashMap<String, ArrayList<Flashcard>> flashcards;

    private static final String deleteDialogTitle = "Usuwanie kategorii:";
    private static final String deleteDialogInfo = "Czy na pewno chcesz usunąć tę kategorię?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        chooseFlashcards();

        listViewCategories = findViewById(R.id.list_view_object);
        categoryAdapter = new CategoryAdapter(
                this,
                R.layout.list_view_category,
                Data.categories,
                flashcards,
                this);
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

    public void openSimpleDeleteDialog(String category) {
        simpleDeleteDialog = new SimpleDeleteDialog(this, category, deleteDialogTitle, deleteDialogInfo);
        simpleDeleteDialog.show(getSupportFragmentManager(), "Open Simple Delete Dialog");
    }

    @Override
    public void simpleDelete(Object object) {
        Data.removeCategory((String) object);
        categoryAdapter.notifyDataSetChanged();
    }
}
