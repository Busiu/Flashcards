package com.example.flashcards.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.flashcards.model.Flashcard;
import com.example.flashcards.R;
import com.example.flashcards.model.Language;
import com.example.flashcards.model.Phrase;

import java.util.ArrayList;

public class AddFlashcardDialog extends AppCompatDialogFragment {

    private EditText editTextEnglishPhrase;
    private EditText editTextPolishPhrase;
    private Spinner spinnerCategory;
    private ArrayAdapter<String> arrayAdapterCategories;
    private ArrayList<String> listOfCategories;

    private AddFlashcardDialogListener listener;

    private String chosenCategory;

    public AddFlashcardDialog(
            AddFlashcardDialogListener listener,
            ArrayList<String> listOfCategories) {
        this.listener = listener;
        this.listOfCategories = listOfCategories;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_flashcard, null);
        arrayAdapterCategories = new ArrayAdapter<>(getContext(), R.layout.spinner_simple_element, listOfCategories);

        editTextEnglishPhrase = view.findViewById(R.id.edit_text_english_phrase);
        editTextPolishPhrase = view.findViewById(R.id.edit_text_polish_phrase);

        spinnerCategory = view.findViewById(R.id.spinner_category);
        spinnerCategory.setAdapter(arrayAdapterCategories);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosenCategory = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builder.setView(view)
                .setTitle("Dodaj fiszkę")
                .setNegativeButton("anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("dodaj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String englishPhrase = editTextEnglishPhrase.getText().toString();
                        String polishPhrase = editTextPolishPhrase.getText().toString();
                        listener.addFlashcard(new Flashcard(
                                new Phrase(Language.ENGLISH, englishPhrase),
                                new Phrase(Language.POLISH, polishPhrase),
                                chosenCategory));
                    }
                });

        return builder.create();
    }

    public interface AddFlashcardDialogListener {
        void addFlashcard(Flashcard flashcard);
    }
}
