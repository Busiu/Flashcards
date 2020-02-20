package com.example.flashcards.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.flashcards.R;
import com.example.flashcards.activities.FlashcardListActivity;
import com.example.flashcards.model.Flashcard;

import java.util.ArrayList;

public class FlashcardAdapter extends ArrayAdapter<Flashcard> {

    private FlashcardListActivity activity;
    private ArrayList<Flashcard> flashcards;

    public FlashcardAdapter(Context context, int layoutResourceId, ArrayList<Flashcard> flashcards, FlashcardListActivity activity) {
        super(context, layoutResourceId, flashcards);
        this.activity = activity;
        this.flashcards = flashcards;
    }

    private class ViewHolder {
        private Button buttonDelete;
        private TextView textViewEnglishPhrase;
        private TextView textViewPolishPhrase;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.list_view_flashcard, parent, false);

            viewHolder.buttonDelete = convertView.findViewById(R.id.button_delete);
            viewHolder.textViewEnglishPhrase = convertView.findViewById(R.id.text_view_english_phrase);
            viewHolder.textViewPolishPhrase = convertView.findViewById(R.id.text_view_polish_phrase);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Flashcard flashcard = flashcards.get(position);
        /*
        viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openSimpleDeleteDialog(flashcard);
            }
        });
         */
        viewHolder.textViewEnglishPhrase.setText(flashcard.getEnglishPhrase().toString());
        viewHolder.textViewPolishPhrase.setText(flashcard.getPolishPhrase().toString());

        return convertView;
    }

    /*
    public void delete(Flashcard flashcard) {
        flashcards.remove(flashcard);
        notifyDataSetChanged();
    }
    */
}
