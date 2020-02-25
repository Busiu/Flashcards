package com.example.flashcards.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.flashcards.R;
import com.example.flashcards.adapters.LevelPagerAdapter;
import com.example.flashcards.fragments.FlashcardListFragment;
import com.example.flashcards.model.Level;
import com.google.android.material.tabs.TabLayout;

public class FlashcardListActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPagerLevels;
    private LevelPagerAdapter levelPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_new);

        tabLayout = findViewById(R.id.tabs);
        viewPagerLevels = findViewById(R.id.view_pager_levels);
        levelPagerAdapter = new LevelPagerAdapter(this, getSupportFragmentManager());
        levelPagerAdapter.addItem(new FlashcardListFragment(Level.KNOWN));
        levelPagerAdapter.addItem(new FlashcardListFragment(Level.UNKNOWN));
        viewPagerLevels.setAdapter(levelPagerAdapter);
        tabLayout.setupWithViewPager(viewPagerLevels);
    }
}
