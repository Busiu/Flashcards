package com.example.flashcards.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.flashcards.fragments.FlashcardListFragment;

import java.util.ArrayList;

public class LevelPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private ArrayList<FlashcardListFragment> fragments;

    public LevelPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        this.fragments = new ArrayList<>();
    }

    public void addItem(FlashcardListFragment fragment) {
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getLevel().toString();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
