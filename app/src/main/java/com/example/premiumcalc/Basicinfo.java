package com.example.premiumcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Basicinfo extends AppCompatActivity {

    SeekBar Age;
    SeekBar Lifecover;
    SeekBar Term;

    TextView Agetxt;
    TextView Lifecovertxt;
    TextView Termtext;
    TextView Termlength;
    EditText namee;
    RadioGroup rg;
    RadioButton male;
    RadioButton female;
    CheckBox smokee;
    CheckBox alcohol;
    CheckBox chrono;


    public static int age1;
    public static String gender;
    public static String nameee;
    public static int lifecvr;
    public static int years;
    public static boolean smoke = false;
    public static boolean alcoholic = false;
    public static boolean chronic = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicinfo);
        Button btn2 = (Button)findViewById(R.id.Basicinfo_btn1);

        Age = (SeekBar)findViewById(R.id.seekbar_age);
        Lifecover = (SeekBar)findViewById(R.id.seekbar_lifecover);
        Term = (SeekBar)findViewById(R.id.seekbar_term);
        Agetxt = (TextView)findViewById(R.id.Ageview);
        Lifecovertxt= (TextView)findViewById(R.id.Lifetxt);
        Termtext = (TextView)findViewById(R.id.termtxt);
        Termlength = (TextView)findViewById(R.id.termlen);
        namee = (EditText)findViewById(R.id.name);
        rg = (RadioGroup)findViewById(R.id.rg1);
        smokee = (CheckBox)findViewById(R.id.smk);
        alcohol = (CheckBox)findViewById(R.id.alc);
        chrono = (CheckBox)findViewById(R.id.chr);


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i){
                        case R.id.radioButton3:
                            gender = "Male";
                            break;
                        case R.id.radioButton4:
                            gender = "Female";
                            break;
                    }
            }
        });
        Age.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
            int prog = 18;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int agee;
                agee = prog +18;
                age1 = agee;
                prog  = i;
                Agetxt.setText(String.valueOf(prog+18));
                if(agee<=40){
                    Term.setMax(20);
                    Termlength.setText("30");

                }
                else if(agee>40 && agee<=45){
                    Term.setMax(15);
                    Termlength.setText("25");
                }
                else if(agee>45 && agee<=50){
                    Term.setMax(10);
                    Termlength.setText("20");
                }
                else if(agee>50 && agee<=55){
                    Term.setMax(5);
                    Termlength.setText("15");;
                }
                else if(agee>55 && agee<=60){
                    Term.setMax(0);
                    Termlength.setText("10");
                }
                else {
                    Term.setMax(0);
                    Termlength.setText("10");
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }));

        Lifecover.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
            int prog = 0;


            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                prog  = i;
                lifecvr = (prog*100000)+1000000;
                Lifecovertxt.setText(String.valueOf((prog*100000)+1000000));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }));
        Term.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
            int prog = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int yourStep = 5;
                i = ((int)Math.round(i/yourStep ))*yourStep;
                seekBar.setProgress(i);
                prog  = i;
                Termtext.setText(String.valueOf((prog+10))+ " years");
                years = prog+10;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }));



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valid = namee.getText().toString();
                if(valid.matches(""))
                {
                    Toast.makeText(Basicinfo.this,"You did not enter a username", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (rg.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(Basicinfo.this,"You did not selected a gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {


                    nameee = namee.getText().toString();

                    if (smokee.isChecked()) {
                        Results.smokerate = 20.0;
                    } else {
                        Results.smokerate = 0;
                    }

                    if (alcohol.isChecked()) {
                        Results.alcoholrate = 15.0;
                    } else {
                        Results.alcoholrate = 0;
                    }
                    if (chrono.isChecked()) {
                        Results.chronorate = 23.0;
                    } else {
                        Results.chronorate = 0;
                    }

                    Intent i = new Intent(getApplicationContext(), Results.class);
                    startActivity(i);
                }
            }
        });
    }
}