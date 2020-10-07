package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean simpleInterestSelected = true;
    private int timeDurationSelected = 1;
    Spinner spinner;
    String string[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    public EditText principalAmountEditText, interestRateEditText, timeDurationEditText, nameEditText;
    public TextView outputTextView;
    public Button calculateAction;
    private RadioGroup interestTypeRadio;
    private RadioButton simpleInterestRadio;
    private RadioButton compoundInterestRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        principalAmountEditText = findViewById(R.id.amt);
        interestRateEditText = findViewById(R.id.interest);
        spinner = findViewById(R.id.spinner);

        outputTextView = findViewById(R.id.Output);

        calculateAction = findViewById(R.id.calculateAction);

        nameEditText = findViewById(R.id.name);

        interestTypeRadio = findViewById(R.id.interestTypeRadio);
        simpleInterestRadio = findViewById(R.id.simpleInterestRadio);
        compoundInterestRadio = findViewById(R.id.compoundInterestRadio);

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, string);
        spinner.setAdapter(ad);

        interestTypeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.simpleInterestRadio) {
                    simpleInterestSelected = true;
                } else {
                    simpleInterestSelected = false;
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        timeDurationSelected = 2;
                        break;
                    case 2:
                        timeDurationSelected = 3;
                    case 3:
                        timeDurationSelected = 4;
                        break;
                    case 4:
                        timeDurationSelected = 5;
                        break;
                    case 5:
                        timeDurationSelected = 6;
                        break;
                    case 6:
                        timeDurationSelected = 7;
                        break;
                    case 7:
                        timeDurationSelected = 8;
                        break;
                    case 8:
                        timeDurationSelected = 9;
                        break;
                    case 9:
                        timeDurationSelected = 10;
                        break;
                    case 10:
                        timeDurationSelected = 11;
                        break;
                    case 11:
                        timeDurationSelected = 12;
                        break;
                    case 12:
                        timeDurationSelected = 13;
                        break;
                    case 13:
                        timeDurationSelected = 14;
                        break;
                    case 14:
                        timeDurationSelected = 15;
                        break;
                    case 15:
                        timeDurationSelected = 16;
                        break;
                    case 16:
                        timeDurationSelected = 17;
                        break;
                    case 17:
                        timeDurationSelected = 18;
                        break;
                    case 18:
                        timeDurationSelected = 19;
                        break;
                    case 19:
                        timeDurationSelected = 20;
                        break;
                    default:
                        timeDurationSelected = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calculateAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double principalAmt = Double.MIN_VALUE, interestRate = Double.MIN_VALUE;
                String name = nameEditText.getText() != null ? nameEditText.getText().toString() : null;
                try {
                    principalAmt = Double.parseDouble(principalAmountEditText.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    interestRate = Double.parseDouble(interestRateEditText.getText() != null ? interestRateEditText.getText().toString() : String.valueOf(-1));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (name == null || name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your full name", Toast.LENGTH_LONG).show();
                    return;
                }
                if (principalAmt == Double.MIN_VALUE) {
                    Toast.makeText(MainActivity.this, "Please enter your principal amount", Toast.LENGTH_LONG).show();
                    return;
                }
                if (interestRate == Double.MIN_VALUE) {
                    Toast.makeText(MainActivity.this, "Please enter your interest rate", Toast.LENGTH_LONG).show();
                    return;
                }

                double interest = calculateInterest(principalAmt, interestRate, timeDurationSelected, simpleInterestSelected);
                showOutputMsg(name, principalAmt, interestRate, timeDurationSelected, interest);
            }

        });
    }

    /**
     * @param pricipalAmount
     * @param interestRate
     * @param timeDuration
     * @param isSimple
     * @return
     */
    private double calculateInterest(double pricipalAmount, double interestRate, int timeDuration, boolean isSimple) {
        if (isSimple)
            return pricipalAmount * interestRate * timeDuration / 100;
        else
            return pricipalAmount * Math.pow((1 + interestRate / 100), timeDuration) - pricipalAmount;
    }

    private void showOutputMsg(String name, double pricipalAmount, double interestRate, int timeDuration, double interest) {
        outputTextView.setText("Hi " + name + ", on the deposit amount of " + pricipalAmount + " Rs for " + timeDuration + " years at " + interestRate + " % interest rate, you will be payed a monthly interest of " + interest + " Rs Thanks.");
    }
}


