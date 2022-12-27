package com.ase.sanoapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ase.sanoapp.R;
import com.ase.sanoapp.advice.ParseActivity;
import com.ase.sanoapp.bmicalculator.BmiMainActivity;
import com.ase.sanoapp.model.Common.Common;
import com.ase.sanoapp.treatment.MedicineApp;
import com.ase.sanoapp.treatment.medicine.MedicineActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {
    ImageView SignOutBtn;
    LinearLayout searchPatBtn;
    LinearLayout myDoctors;
    LinearLayout BtnRequst;
    LinearLayout calculator;
    LinearLayout medicalEdu;
    LinearLayout btnCallSOS;
    LinearLayout btnTreatement;
    ImageView profile;
    LinearLayout appointment;
    TextView nameUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu_home);
        nameUser = findViewById(R.id.textViewName);

        btnCallSOS = findViewById(R.id.callSOS);
        btnCallSOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:112"));
                startActivity(intent);
            }
        });

        appointment = findViewById(R.id.appointement2);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(HomeActivity.this, PatientAppointementsActivity.class);
                startActivity(k);
            }
        });
        searchPatBtn = findViewById(R.id.searchBtn);
        searchPatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(HomeActivity.this, SearchPatActivity.class);
                startActivity(k);
            }
        });
        SignOutBtn=findViewById(R.id.signOutBtn);
        SignOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        myDoctors = findViewById(R.id.myDoctors);
        myDoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(HomeActivity.this, MyDoctorsAvtivity.class);
                startActivity(k);
            }
        });

        BtnRequst = findViewById(R.id.btnRequstFolder);
        BtnRequst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FolderMedical.class);
                intent.putExtra("patient_email",FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
                startActivity(intent);
            }
        });

        profile = findViewById(R.id.profile_btn);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(HomeActivity.this, ProfilePatientActivity.class);
                startActivity(k);
            }
        });

        medicalEdu = findViewById(R.id.medicalEducation);
        medicalEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(HomeActivity.this, ParseActivity.class);
                startActivity(k);
            }
        });

        calculator = findViewById(R.id.calculatorBMI);
        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(HomeActivity.this, BmiMainActivity.class);
                startActivity(k);
            }
        });

        btnTreatement = findViewById(R.id.treatement);
        btnTreatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(HomeActivity.this, MedicineActivity.class);
                startActivity(k);
            }
        });



        Common.CurrentUserid= FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        FirebaseFirestore.getInstance().collection("User").document(Common.CurrentUserid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Common.CurrentUserName = documentSnapshot.getString("name");
                nameUser.setText(documentSnapshot.getString("name"));
            }
        });

    }
}
