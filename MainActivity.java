package com.example.cardgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CanvasView canvasView;
    private TextView cardTextView;
    private int step = 0;
    private String colour = "";
    private String suit = "";
    private String binaryValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvasView = findViewById(R.id.canvasView);
        cardTextView = findViewById(R.id.cardTextView);

        canvasView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String direction = event.getX() < canvasView.getWidth() / 2 ? "down" : "up";
                    handleVolumeChange(direction);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            handleVolumeChange("up");
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            handleVolumeChange("down");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void handleVolumeChange(String direction) {
        if (step == 0) {
            colour = direction.equals("up") ? "piros" : "fekete";
        } else if (step == 1) {
            suit = getSuit(colour, direction);
        } else if (step >= 2 && step < 6) {
            binaryValue += direction.equals("up") ? "1" : "0";
        }
        step++;
        if (step == 6) {
            String cardValue = getCardValue(binaryValue);
            displayCard(colour, suit, cardValue);
        }
    }

    private String getSuit(String colour, String direction) {
        if (colour.equals("piros")) {
            return direction.equals("up") ? "kör" : "káró";
        } else {
            return direction.equals("up") ? "pikk" : "treff";
        }
    }

    private String getCardValue(String binary) {
        switch (binary) {
            case "0001": return "A";
            case "0010": return "2";
            case "0011": return "3";
            case "0100": return "4";
            case "0101": return "5";
            case "0110": return "6";
            case "0111": return "7";
            case "1000": return "8";
            case "1001": return "9";
            case "1010": return "10";
            case "1011": return "J";
            case "1100": return "Q";
            case "1101": return "K";
            case "1111": return "A";
            default: return "Ismeretlen";
        }
    }

    private void displayCard(String colour, String suit, String value) {
        String suitSymbol = "";
        int cardColour = Color.BLACK;
        if (colour.equals("piros")) {
            cardColour = Color.RED;
            suitSymbol = suit.equals("kör") ? "♥" : "♦";
        } else {
            suitSymbol = suit.equals("pikk") ? "♠" : "♣";
        }

        String cardText = suitSymbol + " " + value;
        cardTextView.setText(cardText);
        cardTextView.setTextColor(cardColour);
        cardTextView.setVisibility(View.VISIBLE);
        canvasView.setVisibility(View.GONE);
    }
}
