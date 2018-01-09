package com.example.admin.mathcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mCalculatorDisplay;
    private static final String DIGITS = "0123456789.←";
    private Boolean userIsInTheMiddleOfTypingANumber = false;

    DecimalFormat df = new DecimalFormat("@###########");

    CalculatorBrain mCalculatorBrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalculatorBrain = new CalculatorBrain();

        mCalculatorDisplay = (TextView) findViewById(R.id.txt_result);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);

        (findViewById(R.id.zero)).setOnClickListener(this);
        (findViewById(R.id.one)).setOnClickListener(this);
        (findViewById(R.id.two)).setOnClickListener(this);
        (findViewById(R.id.three)).setOnClickListener(this);
        (findViewById(R.id.four)).setOnClickListener(this);
        (findViewById(R.id.five)).setOnClickListener(this);
        (findViewById(R.id.six)).setOnClickListener(this);
        (findViewById(R.id.seven)).setOnClickListener(this);
        (findViewById(R.id.eight)).setOnClickListener(this);
        (findViewById(R.id.nine)).setOnClickListener(this);

        (findViewById(R.id.MC)).setOnClickListener(this);
        (findViewById(R.id.MR)).setOnClickListener(this);
        (findViewById(R.id.MS)).setOnClickListener(this);
        (findViewById(R.id.M_plus)).setOnClickListener(this);
        (findViewById(R.id.M_minus)).setOnClickListener(this);
        (findViewById(R.id.back)).setOnClickListener(this);
        (findViewById(R.id.CE)).setOnClickListener(this);
        (findViewById(R.id.C)).setOnClickListener(this);
        (findViewById(R.id.plus_minus)).setOnClickListener(this);
        (findViewById(R.id.sqrt)).setOnClickListener(this);
        (findViewById(R.id.plus)).setOnClickListener(this);
        (findViewById(R.id.minus)).setOnClickListener(this);
        (findViewById(R.id.multiply)).setOnClickListener(this);
        (findViewById(R.id.divide)).setOnClickListener(this);
        (findViewById(R.id.dot)).setOnClickListener(this);
        (findViewById(R.id.equal)).setOnClickListener(this);
        (findViewById(R.id.percent)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String buttonPressed = ((Button) v).getText().toString();
        if (DIGITS.contains(buttonPressed)) {
            if (!buttonPressed.equals("←")){
            if (userIsInTheMiddleOfTypingANumber) {
                if (buttonPressed.equals(".") && mCalculatorDisplay.getText().toString().contains(".")) {
                } else {
                        mCalculatorDisplay.append(buttonPressed);
                }

            } else {
                if (buttonPressed.equals(".")) {
                    mCalculatorDisplay.setText(0 + buttonPressed);
                } else {
                        mCalculatorDisplay.setText(buttonPressed);
                }
                userIsInTheMiddleOfTypingANumber = true;
            }}

            if (buttonPressed.equals("←") && mCalculatorDisplay.getText().toString() != "") {
                String textNow = mCalculatorDisplay.getText().toString();
                if (textNow.length() > 0){
                    textNow = textNow.substring(0, textNow.length() - 1);
                    mCalculatorDisplay.setText(textNow);
                }else {
                    mCalculatorDisplay.setText("");
                }
            }
        } else {
            // operation was pressed
            if (userIsInTheMiddleOfTypingANumber) {
                mCalculatorBrain.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }
            mCalculatorBrain.performOperation(buttonPressed);
            mCalculatorDisplay.setText(df.format(mCalculatorBrain.getResult()));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save variables on screen orientation change
        outState.putDouble("OPERAND", mCalculatorBrain.getResult());
        outState.putDouble("MEMORY", mCalculatorBrain.getMemory());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore variables on screen orientation change
        mCalculatorBrain.setOperand(savedInstanceState.getDouble("OPERAND"));
        mCalculatorBrain.setMemory(savedInstanceState.getDouble("MEMORY"));
        mCalculatorDisplay.setText(df.format(mCalculatorBrain.getResult()));
    }
}
