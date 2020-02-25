package com.example.flashcards.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.flashcards.fragments.FlashcardListFragment;
import com.example.flashcards.model.Level;

public class LevelPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public LevelPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new FlashcardListFragment(Level.KNOWN);
            case 1:
                return new FlashcardListFragment(Level.UNKNOWN);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return Level.KNOWN.toString();
            case 1:
                return Level.UNKNOWN.toString();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
