package com.example.flashcards;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddCategoryDialog extends AppCompatDialogFragment {

    private EditText editTextCategoryName;
    private AddCategoryDialogListener listener;

    public AddCategoryDialog(AddCategoryDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_category, null);

        editTextCategoryName = view.findViewById(R.id.edit_text_category_name);

        builder.setView(view)
                .setTitle("Dodaj kategorie")
                .setNegativeButton("anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("dodaj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String categoryName = editTextCategoryName.getText().toString();
                        listener.addCategory(categoryName);
                    }
                });

        return builder.create();
    }

    public interface AddCategoryDialogListener {
        void addCategory(String category);
    }
}

