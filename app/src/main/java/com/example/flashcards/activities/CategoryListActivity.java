package com.example.flashcards.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashcards.R;
import com.example.flashcards.adapters.CategoryAdapter;
import com.example.flashcards.database.Data;
import com.example.flashcards.dialogs.AddCategoryDialog;
import com.example.flashcards.dialogs.CategoryOptionsDialog;
import com.example.flashcards.dialogs.SimpleDeleteDialog;
import com.example.flashcards.model.Level;
import com.example.flashcards.model.Flashcard;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryListActivity extends AppCompatActivity implements
        SimpleDeleteDialog.SimpleDeleteDialogListener,
        AddCategoryDialog.AddCategoryDialogListener {

    private FloatingActionButton fabAddCategory;
    private ListView listViewCategories;
    private CategoryAdapter categoryAdapter;

    private AddCategoryDialog addCategoryDialog;
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

        fabAddCategory = findViewById(R.id.fab_add_object);
        fabAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCategoryDialog();
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

    private void openAddCategoryDialog() {
        addCategoryDialog = new AddCategoryDialog(this);
        addCategoryDialog.show(getSupportFragmentManager(), "Adding category");
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
    public void addCategory(String category) {
        boolean result = Data.addCategory(category);
        if (result) {
            Toast.makeText(this, "Udało się dodać kategorię!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Taka kategoria już isnieje!", Toast.LENGTH_SHORT).show();
        }
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void simpleDelete(Object object) {
        Data.removeCategory((String) object);
        categoryAdapter.notifyDataSetChanged();
    }
}
