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
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.flashcards.database.Data;
import com.example.flashcards.model.Flashcard;
import com.example.flashcards.R;
import com.example.flashcards.model.Language;
import com.example.flashcards.model.Level;
import com.example.flashcards.model.Phrase;

import java.util.ArrayList;

public class AddFlashcardDialog extends AppCompatDialogFragment {

    private EditText editTextEnglishPhrase;
    private EditText editTextPolishPhrase;
    private TextView textViewCategoryName;

    private AddFlashcardDialogListener listener;

    private Level chosenLevel;

    public AddFlashcardDialog(
            AddFlashcardDialogListener listener, Level chosenLevel) {
        this.listener = listener;
        this.chosenLevel = chosenLevel;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_flashcard, null);

        editTextEnglishPhrase = view.findViewById(R.id.edit_text_english_phrase);
        editTextPolishPhrase = view.findViewById(R.id.edit_text_polish_phrase);

        textViewCategoryName = view.findViewById(R.id.text_view_category_name);
        textViewCategoryName.setText(Data.chosenCategory);

        builder.setView(view)
                .setTitle("Dodaj fiszkę:")
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
                                chosenLevel,
                                new Phrase(Language.ENGLISH, englishPhrase),
                                new Phrase(Language.POLISH, polishPhrase),
                                Data.chosenCategory));
                    }
                });

        return builder.create();
    }

    public interface AddFlashcardDialogListener {
        void addFlashcard(Flashcard flashcard);
    }
}
