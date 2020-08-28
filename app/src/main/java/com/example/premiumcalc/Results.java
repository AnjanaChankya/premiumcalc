package com.example.premiumcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Results extends AppCompatActivity {
    DatabaseHelper mydb;

    TextView name1;
    TextView naturaldeath;
    TextView accidential;
    TextView criticaldeath;
    TextView hospitalpay;
    TextView premiummonthly;
    TextView premiumyearly;
    Button sve;

    private double smokerate;
    private double alcoholrate;
    private double chronorate;
    private double policyfee;
    private double genderrate;
    private double termrate;
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        mydb = new DatabaseHelper(this);

        name1 = (TextView)findViewById(R.id.nameview);
        naturaldeath = (TextView)findViewById(R.id.natdeath);
        accidential = (TextView)findViewById((R.id.accdeath));
        criticaldeath = (TextView)findViewById(R.id.critdeath);
        hospitalpay = (TextView)findViewById(R.id.hospay);
        premiummonthly = (TextView)findViewById(R.id.premonth);
        premiumyearly =(TextView)findViewById(R.id.preyear);
        sve = (Button)findViewById(R.id.save);

        addData();








        name1.setText(Basicinfo.nameee);
        naturaldeath.setText(String.valueOf(Basicinfo.lifecvr));
        accidential.setText(String.valueOf(Basicinfo.lifecvr*2));
        criticaldeath.setText(String.valueOf(Basicinfo.lifecvr));






        Double hpay = Basicinfo.lifecvr*0.001;
        if(hpay<5000){
            hospitalpay.setText(String.valueOf(hpay));
        }
        else{
            hospitalpay.setText(("5000"));
        }
        if(Basicinfo.smoke = true){
            smokerate = 20.0;
        }
        else{
            smokerate = 0;
        }
        if(Basicinfo.alcoholic = true){
            alcoholrate = 12.0;
        }
        else{
            alcoholrate = 0;
        }
        if(Basicinfo.chronic = true){
            chronorate = 13.0;
        }
        else{
            chronorate = 0;
        }
        if(Basicinfo.gender == "Male"){
            genderrate = 3.0;
        }
        else{
            genderrate = 1.0;
        }


        Double mortalitycost = (Basicinfo.lifecvr/1000)*(((13.0/21)*Basicinfo.age1)-(64/7));
        Double annualprememium = (((mortalitycost/100) * (smokerate+alcoholrate+chronorate+genderrate))+ (mortalitycost) + (mortalitycost/100*0.6)*Basicinfo.years);
        Double monthlypremium2 = (((annualprememium/100)*8) + annualprememium)/12;

        premiumyearly.setText(String.format("%.2f",annualprememium));
        premiummonthly.setText(String.format("%.2f",monthlypremium2));
    }
    public void addData() {
        sve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isinsterted = mydb.insertData(name1.getText().toString(),naturaldeath.getText().toString(),accidential.getText().toString(),criticaldeath.getText().toString(),hospitalpay.getText().toString(),premiummonthly.getText().toString(),premiumyearly.getText().toString());

                if(isinsterted = true){
                    Toast.makeText(Results.this,"Data Inserted",Toast.LENGTH_LONG).show();


                }
                else{
                    Toast.makeText(Results.this,"Data Not Inserted",Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}