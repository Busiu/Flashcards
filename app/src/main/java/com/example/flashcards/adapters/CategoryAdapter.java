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
import com.example.flashcards.activities.CategoryListActivity;
import com.example.flashcards.model.Flashcard;
import com.example.flashcards.utils.NoDuplicateArrayList;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryAdapter extends ArrayAdapter<String> {

    private CategoryListActivity activity;
    private NoDuplicateArrayList<String> categories;
    private HashMap<String, ArrayList<Flashcard>> knownFlashcards;
    private HashMap<String, ArrayList<Flashcard>> unknownFlashcards;

    public CategoryAdapter(
            Context context,
            int layoutResourceId,
            NoDuplicateArrayList<String> categories,
            HashMap<String, ArrayList<Flashcard>> knownFlashcards,
            HashMap<String, ArrayList<Flashcard>> unknownFlashcards,
            CategoryListActivity activity) {
        super(context, layoutResourceId, categories.getArrayList());
        this.activity = activity;
        this.categories = categories;
        this.knownFlashcards = knownFlashcards;
        this.unknownFlashcards = unknownFlashcards;
    }

    private class ViewHolder {
        private Button buttonDelete;
        private TextView textViewCategoryName;
        private TextView textViewNoKnownFlashcards;
        private TextView textViewNoUnknownFlashcards;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.list_view_category, parent, false);

            viewHolder.buttonDelete = convertView.findViewById(R.id.button_delete);
            viewHolder.textViewCategoryName = convertView.findViewById(R.id.text_view_category_name);
            viewHolder.textViewNoKnownFlashcards = convertView.findViewById(R.id.text_view_no_known_flashcards);
            viewHolder.textViewNoUnknownFlashcards = convertView.findViewById(R.id.text_view_no_unknown_flashcards);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final String category = categories.get(position);
        viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openSimpleDeleteDialog(category);
            }
        });
        viewHolder.textViewCategoryName.setText(category);
        viewHolder.textViewNoKnownFlashcards.setText(Integer.toString(knownFlashcards.get(category).size()));
        viewHolder.textViewNoUnknownFlashcards.setText(Integer.toString(unknownFlashcards.get(category).size()));

        return convertView;
    }
}
