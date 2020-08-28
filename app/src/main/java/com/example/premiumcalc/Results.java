package com.example.premiumcalc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
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
    Button viewres;
    Button recal;

    public static  double smokerate;
    public static double  alcoholrate;
    public static double chronorate;

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
        viewres = (Button)findViewById((R.id.view));

        addData();
        viewres();



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

        /*if(Basicinfo.smoke = true){
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
        }*/
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
    public void viewres(){
        viewres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Cursor res =  mydb.getAllData();
               if(res.getCount() == 0){
                   showmessage("Error","Nothing found");
                   return;
               }
               StringBuffer buffer  = new StringBuffer();
               while(res.moveToNext()){

                   buffer.append("Name: " +res.getString(1) +"\n");
                   buffer.append("Cover on Natural Death : " +res.getString(2) +"\n");
                   buffer.append("Cover on Accident Death " +res.getString(3) +"\n");
                   buffer.append("Cover on illness Death " +res.getString(4) +"\n");
                   buffer.append("Cover on hospital per day: " +res.getString(5) +"\n");
                   buffer.append("Monthly premium: " +res.getString(6) +"\n");
                   buffer.append("Annual premium: " +res.getString(7) +"\n\n\n");

               }
               showmessage("Data",buffer.toString());
            }
        });
    }
    public void showmessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}