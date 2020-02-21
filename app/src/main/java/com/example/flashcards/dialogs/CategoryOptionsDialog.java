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
import com.example.flashcards.activities.FlashcardListActivity;
import com.example.flashcards.model.Compartment;
import com.example.flashcards.model.CompartmentType;
import com.example.flashcards.utils.ChosenObjects;

public class CategoryOptionsDialog extends AppCompatDialogFragment {

    private Button buttonShowFlashcards;
    private Button buttonPlay;
    private FlashcardGameOptionsDialog flashcardGameOptionsDialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_category_options, null);

        buttonShowFlashcards = view.findViewById(R.id.button_show_flashcards);
        buttonShowFlashcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFlashcardListActivity();
            }
        });

        if (ChosenObjects.currentlyChosenCompartmentType == CompartmentType.UNKNOWN) {
            System.out.println("XDDDDDDDDDDDDDDD");
            buttonPlay = view.findViewById(R.id.button_play);
            buttonPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFlashcardGameOptionsDialog();
                }
            });
        }

        builder.setView(view)
                .setTitle("Co chcesz zrobić?");

        return builder.create();
    }

    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }

    private void openFlashcardGameOptionsDialog() {
        flashcardGameOptionsDialog = new FlashcardGameOptionsDialog();
        flashcardGameOptionsDialog.show(getChildFragmentManager(), "Flashcard Game Options");
    }

    private void openFlashcardListActivity() {
        Intent intent = new Intent(getContext(), FlashcardListActivity.class);
        startActivity(intent);
    }
}
