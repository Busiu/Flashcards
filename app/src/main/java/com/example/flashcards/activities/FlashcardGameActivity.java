package com.example.flashcards.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashcards.R;
import com.example.flashcards.database.Data;
import com.example.flashcards.model.Level;
import com.example.flashcards.model.Flashcard;
import com.example.flashcards.model.FlashcardGameType;
import com.example.flashcards.model.Language;
import com.example.flashcards.model.Phrase;

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

    private Level chosenLevel;
    private FlashcardGameType chosenFlashcardGameType;
    private String chosenCategory;

    private static final String theEnd = "Wykorzystano wszystkie karty! Powróć do poprzedniego okienka!";

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
                onButtonDoNotKnowClick();
            }
        });
        buttonKnow = findViewById(R.id.button_know);
        buttonKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonKnowClick();
            }
        });
        buttonTurnOver = findViewById(R.id.button_turn_over);
        buttonTurnOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonTurnOverClick();
            }
        });

        textViewPhrase = findViewById(R.id.text_view_phrase);

        onGameStart();
    }

    private void changeFlashcard() {
        if (iteratorUnknownFlashcardsDeck < sizeUnknownFlashcardsDeck) {
            currentFlashcard = unknownFlashcardsDeck.get(iteratorUnknownFlashcardsDeck);

            if (chosenFlashcardGameType == FlashcardGameType.ENGLISH_TO_POLISH) {
                currentPhrase = currentFlashcard.getEnglishPhrase();
            } else {
                currentPhrase = currentFlashcard.getPolishPhrase();
            }

            textViewPhrase.setText(currentPhrase.toString());
        } else {
            textViewPhrase.setText(theEnd);
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

    private void onButtonDoNotKnowClick() {
        if (iteratorUnknownFlashcardsDeck < sizeUnknownFlashcardsDeck) {
            iteratorUnknownFlashcardsDeck++;
            changeFlashcard();
        } else {
            textViewPhrase.setText(theEnd);
        }
    }

    private void onButtonKnowClick() {
        if (iteratorUnknownFlashcardsDeck < sizeUnknownFlashcardsDeck) {
            unknownFlashcardsDeck.remove(currentFlashcard);
            currentFlashcard.setLevel(Level.KNOWN);
            knownFlashcardsDeck.add(currentFlashcard);
            Data.switchLevelOfFlashcardToDatabase(currentFlashcard);

            sizeUnknownFlashcardsDeck = unknownFlashcardsDeck.size();
            changeFlashcard();
        } else {
            textViewPhrase.setText(theEnd);
        }
    }

    private void onButtonTurnOverClick() {
        if (iteratorUnknownFlashcardsDeck < sizeUnknownFlashcardsDeck) {
            flipCard();
        } else {
            textViewPhrase.setText(theEnd);
        }
    }

    private void onGameStart() {
        if (iteratorUnknownFlashcardsDeck < sizeUnknownFlashcardsDeck) {
            changeFlashcard();
        } else {
            textViewPhrase.setText(theEnd);
        }
    }

    private void setChoice() {
        chosenLevel = Data.chosenLevel;
        chosenFlashcardGameType = Data.chosenFlashcardGameType;
        chosenCategory = Data.chosenCategory;
    }

    private void setDeck() {
        knownFlashcardsDeck = Data.knownFlashcards.get(chosenCategory);
        unknownFlashcardsDeck = Data.unknownFlashcards.get(chosenCategory);
        iteratorUnknownFlashcardsDeck = 0;
        sizeUnknownFlashcardsDeck = unknownFlashcardsDeck.size();
    }

    private void shuffleDeck() {
        Collections.shuffle(unknownFlashcardsDeck);
    }
}
