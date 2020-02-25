package com.example.flashcards.fragments;

import androidx.fragment.app.Fragment;

import com.example.flashcards.model.Level;

public class FlashcardListFragment extends Fragment {

    private Level level;

    public FlashcardListFragment(Level level) {
        this.level = level;
    }
}
