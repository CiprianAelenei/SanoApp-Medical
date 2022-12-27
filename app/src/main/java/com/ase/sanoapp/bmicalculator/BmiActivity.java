package com.ase.sanoapp.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ase.sanoapp.R;
import com.ase.sanoapp.controller.HomeActivity;

public class BmiActivity extends AppCompatActivity {

    TextView mbmidisplay,magedisplay,mweightdisplay,mheightdisplay,mbmicategory,mgender;
    Button mgotomain;
    Intent intent;

    ImageView mimageview;
    String mbmi;
    String cateogory;
    float intbmi;

    String height;
    String weight;

    float intheight,intweight;

    RelativeLayout mbackground;

    @Override
    public void onBackPressed() {
        Intent intent2=new Intent(getApplicationContext(), BmiMainActivity.class);
        startActivity(intent2);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#1E1D1D"));
        intent=getIntent();
        mbmidisplay=findViewById(R.id.bmidisplay);
        mbmicategory = findViewById(R.id.bmicategorydispaly);
        mgotomain=findViewById(R.id.gotomain);
        mimageview=findViewById(R.id.imageview);
        mgender=findViewById(R.id.genderdisplay);
        mbackground=findViewById(R.id.contentlayout);
        height=intent.getStringExtra("height");
        weight=intent.getStringExtra("weight");
        intheight=Float.parseFloat(height);
        intweight=Float.parseFloat(weight);
        intheight=intheight/100;
        intbmi=intweight/(intheight*intheight);
        mbmi=Float.toString(intbmi);
        System.out.println(mbmi);

        if (intbmi < 16) {
            mbmicategory.setText("Subponderal Sever");
            mbmicategory.setTextColor(Color.parseColor("#FD0000"));
            mimageview.setImageResource(R.drawable.un_healthy3);
        } else if (intbmi < 16.9 && intbmi > 16) {
            mbmicategory.setText("Subponderal Moderat");
            mimageview.setImageResource(R.drawable.un_healthy3);
            mbmicategory.setTextColor(Color.parseColor("#833104"));
        } else if (intbmi < 18.4 && intbmi > 17) {
            mbmicategory.setText("Subponderal");
            mimageview.setImageResource(R.drawable.cross_healthy);
            mbmicategory.setTextColor(Color.parseColor("#775500"));
        } else if (intbmi < 24.9 && intbmi > 18.5) {
            mbmicategory.setText("Normoponderal");
            mbmicategory.setTextColor(Color.parseColor("#006F00"));
            mimageview.setImageResource(R.drawable.ok_healthy);
        } else if (intbmi < 29.9 && intbmi > 25) {
            mbmicategory.setText("Supraponderal");
            mimageview.setImageResource(R.drawable.cross_healthy);
            mbmicategory.setTextColor(Color.parseColor("#775500"));
        } else if (intbmi < 34.9 && intbmi > 30) {
            mbmicategory.setText("Obezitate grad I");
            mimageview.setImageResource(R.drawable.un_healthy3);
            mbmicategory.setTextColor(Color.parseColor("#833104"));
        } else {
            mbmicategory.setText("Obezitate grad II");
            mimageview.setImageResource(R.drawable.un_healthy3);
            mbmicategory.setTextColor(Color.parseColor("#FD0000"));
        }
        String gender = intent.getStringExtra("gender");
        if(gender.equals("Male"))
            mgender.setText("Barbat");
        else if(gender.equals("Female"))
            mgender.setText("Femeie");
        mbmidisplay.setText(mbmi);
        mgotomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(), BmiMainActivity.class);
                startActivity(intent1);
            }
        });


    }
}