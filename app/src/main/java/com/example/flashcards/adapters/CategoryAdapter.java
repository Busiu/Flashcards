package com.example.flashcards.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.flashcards.R;
import com.example.flashcards.utils.NoDuplicateArrayList;

public class CategoryAdapter extends ArrayAdapter<String> {

    private NoDuplicateArrayList<String> categories;

    public CategoryAdapter(Context context, int layoutResourceId, NoDuplicateArrayList<String> categories) {
        super(context, layoutResourceId, categories.getArrayList());
        this.categories = categories;
    }

    private class ViewHolder {
        private TextView textViewCategoryName;
        private TextView textViewNoFlashcards;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.list_view_choose_category, parent, false);

            viewHolder.textViewCategoryName = convertView.findViewById(R.id.text_view_category_name);
            viewHolder.textViewNoFlashcards = convertView.findViewById(R.id.text_view_no_flashcards);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String category = categories.get(position);
        viewHolder.textViewCategoryName.setText(category);
        viewHolder.textViewNoFlashcards.setText("10");

        return convertView;
    }
}
