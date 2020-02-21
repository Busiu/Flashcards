package com.example.flashcards.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashcards.R;
import com.example.flashcards.model.Compartment;
import com.example.flashcards.model.CompartmentType;
import com.example.flashcards.model.Flashcard;
import com.example.flashcards.model.FlashcardGameType;
import com.example.flashcards.model.Language;
import com.example.flashcards.model.Phrase;
import com.example.flashcards.utils.ChosenObjects;

import java.util.ArrayList;
import java.util.Collections;

public class FlashcardGameActivity extends AppCompatActivity {

    private Button buttonDoNotKnow;
    private Button buttonKnow;
    private Button buttonTurnOver;
    private TextView textViewPhrase;

    private Flashcard currentFlashcard;
    private Phrase currentPhrase;

    private ArrayList<Flashcard> knownFlashcardsDeck;
    private ArrayList<Flashcard> unknownFlashcardsDeck;
    private int iteratorUnknownFlashcardsDeck;
    private int sizeUnknownFlashcardsDeck;

    private CompartmentType chosenCompartmentType;
    private FlashcardGameType chosenFlashcardGameType;
    private String chosenCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_game_activity);
        setChoice();
        setDeck();
        shuffleDeck();

        buttonDoNotKnow = findViewById(R.id.button_do_not_know);
        buttonDoNotKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iteratorUnknownFlashcardsDeck++;
                changeFlashcard();
            }
        });
        buttonKnow = findViewById(R.id.button_know);
        buttonKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unknownFlashcardsDeck.remove(currentFlashcard);
                knownFlashcardsDeck.add(currentFlashcard);
                sizeUnknownFlashcardsDeck = unknownFlashcardsDeck.size();
                changeFlashcard();
            }
        });
        buttonTurnOver = findViewById(R.id.button_turn_over);
        buttonTurnOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });

        textViewPhrase = findViewById(R.id.text_view_phrase);

        changeFlashcard();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Compartment.save(this);
    }

    private void changeFlashcard() {
        if (iteratorUnknownFlashcardsDeck >= sizeUnknownFlashcardsDeck) {
            textViewPhrase.setText("Wykorzystano wszystkie karty! Powróć do poprzedniego okienka!");
        } else {
            currentFlashcard = unknownFlashcardsDeck.get(iteratorUnknownFlashcardsDeck);

            if (chosenFlashcardGameType == FlashcardGameType.ENGLISH_TO_POLISH) {
                currentPhrase = currentFlashcard.getEnglishPhrase();
            } else {
                currentPhrase = currentFlashcard.getPolishPhrase();
            }

            textViewPhrase.setText(currentPhrase.toString());
        }
    }

    private void flipCard() {
        if (currentPhrase.getLanguage() == Language.ENGLISH) {
            currentPhrase = currentFlashcard.getPolishPhrase();
        } else {
            currentPhrase = currentFlashcard.getEnglishPhrase();
        }
        textViewPhrase.setText(currentPhrase.toString());
    }

    private void setChoice() {
        chosenCompartmentType = ChosenObjects.currentlyChosenCompartmentType;
        chosenFlashcardGameType = ChosenObjects.currentlyChosenFlashcardGameType;
        chosenCategory = ChosenObjects.currentlyChosenCategory;
    }

    private void setDeck() {
        knownFlashcardsDeck = Compartment.knownFlashcards.get(chosenCategory);
        unknownFlashcardsDeck = Compartment.unknownFlashcards.get(chosenCategory);
        iteratorUnknownFlashcardsDeck = 0;
        sizeUnknownFlashcardsDeck = unknownFlashcardsDeck.size();
    }

    private void shuffleDeck() {
        Collections.shuffle(unknownFlashcardsDeck);
    }
}
