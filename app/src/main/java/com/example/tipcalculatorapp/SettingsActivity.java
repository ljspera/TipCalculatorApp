package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    EditText editTip;
    RadioGroup radioGroup2;
    RadioButton split2;
    RadioButton dsplit2;
    EditText editPeople;

    private int Tip;
    private int People;
    private boolean splitChecked;
    private int int_seekBarLabel;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editTip = findViewById(R.id.editTip);
        radioGroup2 = findViewById(R.id.radioGroup2);
        split2 = findViewById(R.id.split2);
        dsplit2 = findViewById(R.id.dsplit2);
        editPeople = findViewById(R.id.editPeople);


        //Intent i = getIntent();
        //Tip = i.getIntExtra("Tip", Tip);
    }


    private void updateTip() {
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        Tip = sp.getInt("editTip", 20);
        int_seekBarLabel = sp.getInt("editTip", 20);
        editTip.setText(""+Tip);
    }

    private void updateSplit(){
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        splitChecked = sp.getBoolean("editChecked", true);
        if(splitChecked){
            split2.setChecked(true);
        }else{
            dsplit2.setChecked(true);
        }
    }

    private void updatePeople(){
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        People = sp.getInt("editPeople", 4);
        editPeople.setText(""+People);
    }


    @Override
    public void onPause(){
        super.onPause();
        if(split2.isChecked()){
            splitChecked = true;
        }
        Tip = Integer.parseInt(editTip.getText().toString());
        int_seekBarLabel = Integer.parseInt(editTip.getText().toString());
        People = Integer.parseInt(editPeople.getText().toString());
        updateSharedPreferences();
    }

    @Override
    public void onResume(){
        super.onResume();
        updateTip();
        updateSplit();
        updatePeople();
    }


    private void updateSharedPreferences() {
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("editTip", Tip);
        editor.putInt("int_seekBarLabel", int_seekBarLabel);
        editor.putInt("editPeople", People);
        editor.putBoolean("editChecked", splitChecked);
        editor.commit();
    }

}