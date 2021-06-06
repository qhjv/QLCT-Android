package com.example.conspendemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import Adapter.ListNganSach;
import DAO.NganSachDB;
import Adapter.NganSach;import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
public class Ngansach extends AppCompatActivity {
    private Button themmoi;
    private RecyclerView re;
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngansach);
        this.back = (ImageButton)this.findViewById(R.id.imageButton2);
        themmoi = findViewById(R.id.themmoi);
        Intent myIntent = getIntent();
        themmoi.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent myIntent = new Intent(Ngansach.this, ThemNganSach.class);

                Ngansach.this.startActivity(myIntent);
            }
        });

        this.back.setOnClickListener(new ImageButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Back to previous Activity.
                Ngansach.this.finish();
            }
        });
    }
}