package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView seekbarLabel;
    EditText inputPurchase;
    TextView total;
    RadioGroup radioGroup;
    RadioButton split;
    RadioButton Dsplit;
    EditText splitNum;
    TextView ifSplit;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        seekbarLabel = findViewById(R.id.seekbarLabel);
        inputPurchase = findViewById(R.id.inputPurchase);
        total = findViewById(R.id.total);
        radioGroup = findViewById(R.id.radioGroup);
        split = findViewById(R.id.split);
        Dsplit = findViewById(R.id.Dsplit);
        splitNum = findViewById(R.id.splitNum);
        ifSplit = findViewById(R.id.ifSplit);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekbarLabel.setText(i + "%");
                String s = inputPurchase.getText().toString();
                double x = Double.parseDouble(s);

                if(split.isChecked()){
                    String sn = splitNum.getText().toString();
                    double xn = Double.parseDouble(sn);
                    double totalOut = ((x*(i/100.0)+x)/xn);
                    double totaltotal = ((x*(i/100.0)+x));
                    total.setText("Each Pay:      "+ String.format("%.2f",totalOut)+"\n"+"Check Total: "+ String.format("%.2f", totaltotal));
                }else{
                    double totalOut = ((x*(i/100.0)+x));
                    total.setText("Total: " + String.format("%.2f", totalOut));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //if(i == R.id.Dsplit) {
                //   ifSplit.setText("Enter number of people below");
                //   splitNum.setHint("Enter: 0");
                //}
                if(i == R.id.split) {
                   // ifSplit.setText("Enter number of people below");
                    splitNum.setHint("ex: 4 people");
                }

            }
        });
    }
}