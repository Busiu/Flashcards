package com.example.flashcards.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.flashcards.R;
import com.example.flashcards.adapters.FlashcardAdapter;
import com.example.flashcards.database.Data;
import com.example.flashcards.dialogs.AddFlashcardDialog;
import com.example.flashcards.dialogs.SimpleDeleteDialog;
import com.example.flashcards.model.Flashcard;
import com.example.flashcards.model.Level;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FlashcardListFragment extends Fragment implements
        AddFlashcardDialog.AddFlashcardDialogListener,
        SimpleDeleteDialog.SimpleDeleteDialogListener {

    private FloatingActionButton fabAddFlashcard;
    private ListView listViewFlashcards;
    private FlashcardAdapter flashcardAdapter;

    private AddFlashcardDialog addFlashcardDialog;
    private SimpleDeleteDialog simpleDeleteDialog;

    private ArrayList<Flashcard> flashcards;

    private Level level;

    private static final String deleteDialogTitle = "Usuwanie fiszki:";
    private static final String deleteDialogInfo = "Czy na pewno chcesz usunąć tę fiszkę?";

    public FlashcardListFragment(Level level) {
        this.level = level;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_view, container, false);
        chooseFlashcards();

        fabAddFlashcard = view.findViewById(R.id.fab_add_object);
        fabAddFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddFlashcardDialog();
            }
        });

        listViewFlashcards = view.findViewById(R.id.list_view_object);
        flashcardAdapter = new FlashcardAdapter(
                getContext(),
                R.layout.list_view_flashcard,
                flashcards,
                this);
        listViewFlashcards.setAdapter(flashcardAdapter);

        return view;
    }

    public Level getLevel() {
        return level;
    }

    private void openAddFlashcardDialog() {
        addFlashcardDialog = new AddFlashcardDialog(this, level);
        addFlashcardDialog.show(getFragmentManager(), "Adding flashcard");
    }

    private void chooseFlashcards() {
        if (level == Level.KNOWN) {
            flashcards = Data.knownFlashcards.get(Data.chosenCategory);
        } else {
            flashcards = Data.unknownFlashcards.get(Data.chosenCategory);
        }
    }

    public void openSimpleDeleteDialog(Flashcard flashcard) {
        simpleDeleteDialog = new SimpleDeleteDialog(this, flashcard, deleteDialogTitle, deleteDialogInfo);
        simpleDeleteDialog.show(getFragmentManager(), "Deleting flashcard");
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        Data.addFlashcard(flashcard);
        Toast.makeText(getContext(), "Udało się dodać fiszkę!", Toast.LENGTH_SHORT).show();
        flashcardAdapter.notifyDataSetChanged();
    }

    @Override
    public void simpleDelete(Object object) {
        Data.removeFlashcard((Flashcard) object);
        flashcardAdapter.notifyDataSetChanged();
    }
}
