package com.example.conspendemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import Adapter.GiaoDich;
import DAO.GiaoDichDbHelper;

public class ThemGD extends AppCompatActivity {
    boolean isupdate;
    int idgiaodich;
    GiaoDich giaodich;
    GiaoDichDbHelper giaodichDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_gd);
        EditText editten = findViewById(R.id.edittext1);
        EditText editngay = findViewById(R.id.editTextDate);
        EditText editsotien = findViewById(R.id.editTextsotien);
        Button savebtn = findViewById(R.id.savebtn);
        Button cancelbtn = findViewById(R.id.cancelbtn);
        //xu ly database
        giaodichDbHelper = new GiaoDichDbHelper(this);
        Intent intent = getIntent();
        isupdate = intent.getBooleanExtra("isupdate",false);
        if(isupdate){
            idgiaodich = intent.getIntExtra("idgiaodich",0);
            giaodich = giaodichDbHelper.getGiaoDichbyID(idgiaodich);


            findViewById(R.id.xoabtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    giaodichDbHelper.deleteGiaodichbyID(idgiaodich);
                    finish();
                }
            });
        }
        else{
            giaodich = new GiaoDich(0,"",0);
            findViewById(R.id.xoabtn).setVisibility(View.GONE);
            savebtn.setText("LUU");
        }
        //gắn sự kiện cho nút
        editten.setText(giaodich.name);
        editsotien.setText(giaodich.price + "");
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giaodich.name = editten.getText().toString();
                giaodich.price = Integer.parseInt(editsotien.getText().toString());
                if(isupdate){
                    giaodichDbHelper.updateGiaoDich(giaodich);
                }else{
                    giaodichDbHelper.insertGiaoDich(giaodich);
                }
                finish();
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemGD.this,Home.class);
                startActivity(intent);
            }
        });
    }
}