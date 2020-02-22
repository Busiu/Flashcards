package com.example.flashcards.dialogs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.flashcards.R;
import com.example.flashcards.activities.FlashcardGameActivity;
import com.example.flashcards.database.Data;
import com.example.flashcards.model.FlashcardGameType;

public class FlashcardGameOptionsDialog extends AppCompatDialogFragment {

    private Button buttonFlashcardGameEnglishToPolish;
    private Button buttonFlashcardGamePolishToEnglish;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_flashcard_game_options, null);

        buttonFlashcardGameEnglishToPolish = view.findViewById(R.id.button_flashcard_game_english_to_polish);
        buttonFlashcardGameEnglishToPolish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFlashcardGameActivity(FlashcardGameType.ENGLISH_TO_POLISH);
            }
        });

        buttonFlashcardGamePolishToEnglish = view.findViewById(R.id.button_flashcard_game_polish_to_english);
        buttonFlashcardGamePolishToEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFlashcardGameActivity(FlashcardGameType.POLISH_TO_ENGLISH);
            }
        });

        builder.setView(view)
                .setTitle("Wybierz tryb nauki:");

        return builder.create();
    }

    private void openFlashcardGameActivity(FlashcardGameType type) {
        Data.chosenFlashcardGameType = type;

        Intent intent = new Intent(getContext(), FlashcardGameActivity.class);
        startActivity(intent);

        dismiss();
    }
}
