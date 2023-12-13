package com.sithum.tiptaptipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sithum.tiptaptipcalculator.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;
    private int selectedButton = 1;
    private SeekBar tipSeekBar;
    private TextView percentageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        double amount = intent.getDoubleExtra("amount", 0.0); // Use "amount" as the key name

        binding.amount.setText(String.valueOf(amount));


        tipSeekBar = findViewById(R.id.tipprecentage);
        percentageTextView = findViewById(R.id.percentageTextView);

        // Set the default progress value to 15%
        tipSeekBar.setProgress(15);
        percentageTextView.setText("15%");
        binding.tipprecent.setText("15%");
        binding.suggesttip.setText("15%");

        tipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String percentage = progress + "%";
                percentageTextView.setText(percentage);
                calculateTip(amount, progress);
                binding.tipprecent.setText(percentage);
                binding.percentageTextView.setText(percentage);
                binding.suggesttip.setText(percentage);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not needed for this example
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not needed for this example
            }
        });

        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splitBill(1);
                setSelectedButton(1);

            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splitBill(2);
                setSelectedButton(2);

            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splitBill(3);
                setSelectedButton(3);

            }
        });

        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splitBill(4);
                setSelectedButton(4);

            }
        });

        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splitBill(5);
                setSelectedButton(5);

            }
        });

        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splitBill(6);
                setSelectedButton(6);

            }
        });

        // Add listeners for other buttons in a similar way

        setSelectedButton(selectedButton);
        calculateTip(amount, 15); // Calculate tip with default 15% progress
    }

    private void setSelectedButton(int buttonNumber) {
        selectedButton = buttonNumber;

        // Reset all button colors to default
        for (int i = 1; i <= 6; i++) {
            int buttonId = getResources().getIdentifier("button" + i, "id", getPackageName());
            AppCompatButton button = findViewById(buttonId);
            button.setBackground(ContextCompat.getDrawable(this, R.drawable.button_default));
        }

        // Set selected button color
        int selectedButtonId = getResources().getIdentifier("button" + selectedButton, "id", getPackageName());
        AppCompatButton selectedButton = findViewById(selectedButtonId);
        selectedButton.setBackground(ContextCompat.getDrawable(this, R.drawable.button_selected));
    }

    private void calculateTip(double amount, int progress) {
        double tipPercentage = (double) progress;
        double tipAmount = amount * (tipPercentage / 100.0);
        double fullAmount = amount + tipAmount;
        String tipAmountString = String.format("%.2f", tipAmount);
        String formattedFullAmount = String.format("%.2f", fullAmount);

        binding.tipamount.setText(tipAmountString);
        binding.tip.setText(tipAmountString);
        binding.fullamount.setText(formattedFullAmount);

        int numberOfPeople = getNumberOfPeople();
        if (numberOfPeople > 0) {
            splitBill(numberOfPeople);
        }
    }

    private int getNumberOfPeople() {
        // Get the currently selected number of people based on the selected button
        int numberOfPeople = 0;
        switch (selectedButton) {
            case 1:
                numberOfPeople = 1;
                break;
            case 2:
                numberOfPeople = 2;
                break;
            case 3:
                numberOfPeople = 3;
                break;
            case 4:
                numberOfPeople = 4;
                break;
            case 5:
                numberOfPeople = 5;
                break;
            case 6:
                numberOfPeople = 6;
                break;
        }
        return numberOfPeople;
    }

    private void splitBill(int numberOfPeople) {
        double fullAmount = Double.parseDouble(binding.fullamount.getText().toString());
        double fulltipAmount = Double.parseDouble(binding.tipamount.getText().toString());
        double splitAmount = fullAmount / numberOfPeople;
        double splittipAmount = fulltipAmount / numberOfPeople;
        String splitAmountString = String.format("%.2f", splitAmount);
        String splittipAmountString = String.format("%.2f", splittipAmount);

        binding.onepersontip.setText(splittipAmountString);
        binding.onepersonamount.setText(splitAmountString);
    }
}