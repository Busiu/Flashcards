package com.example.flashcards.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.flashcards.R;

public class SimpleDeleteDialog extends AppCompatDialogFragment {

    private TextView textViewInfo;
    private SimpleDeleteDialogListener listener;
    private Object chosenObject;

    public SimpleDeleteDialog(SimpleDeleteDialogListener listener, Object chosenObject) {
        this.listener = listener;
        this.chosenObject = chosenObject;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_simple_approval, null);

        textViewInfo = view.findViewById(R.id.text_view_info);

        builder.setView(view)
                .setTitle("Usuwanie fiszki")
                .setNegativeButton("anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("usuń", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.simpleDelete(chosenObject);
                    }
                });

        return builder.create();
    }

    public interface SimpleDeleteDialogListener {
        void simpleDelete(Object object);
    }
}
