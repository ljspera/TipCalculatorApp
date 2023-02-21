package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.Button;
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
    Button calcButton;

    Button settingButton;
    private int Tip;
    private int People;
    private boolean splitChecked;
    private int int_seekBarLabel;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tip = 20;
        People = 4;
        splitChecked = true;
        int_seekBarLabel = Tip;


        seekBar = findViewById(R.id.seekBar);
        seekbarLabel = findViewById(R.id.seekbarLabel);
        inputPurchase = findViewById(R.id.inputPurchase);
        total = findViewById(R.id.total);
        radioGroup = findViewById(R.id.radioGroup);
        split = findViewById(R.id.split);
        Dsplit = findViewById(R.id.Dsplit);
        splitNum = findViewById(R.id.splitNum);
        ifSplit = findViewById(R.id.ifSplit);
        calcButton = findViewById(R.id.calcButton);

        settingButton = findViewById(R.id.settingButton);


        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calculate();
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(i);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(!b)
                    return;
                seekbarLabel.setText(seekBar.getProgress()+ "");
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

    private void Calculate(){
        String s = inputPurchase.getText().toString();
        double x = Double.parseDouble(s);
        String t = seekbarLabel.getText().toString();
        double tt = Double.parseDouble(t);

        if(split.isChecked()){
            String sn = splitNum.getText().toString();
            double xn = Double.parseDouble(sn);
            double totalOut = ((x*(tt/100.0)+x)/xn);
            double totaltotal = ((x*(tt/100.0)+x));
            total.setText("Each Pay:      "+ String.format("%.2f",totalOut)+"\n"+"Check Total: "+ String.format("%.2f", totaltotal));
        }else{
            double totalOut = ((x*(tt/100.0)+x));
            total.setText("Total: " + String.format("%.2f", totalOut));
        }
    }

    private void updateTip() {
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        Tip = sp.getInt("editTip", 20);
        int_seekBarLabel = sp.getInt("editTip", 20);
        seekBar.setProgress(Tip);
        seekbarLabel.setText(int_seekBarLabel+"");
       // Calculate();
    }

    private void updateSplit(){
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        splitChecked = sp.getBoolean("editChecked", true);
        if(splitChecked){
            split.setChecked(true);
        }else{
            Dsplit.setChecked(true);
        }
    }

    private void updatePeople(){
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        People = sp.getInt("editPeople", 4);
        splitNum.setText(""+People);
    }

    @Override
    public void onResume(){
        super.onResume();
        updateTip();
        updateSplit();
        updatePeople();
    }
}