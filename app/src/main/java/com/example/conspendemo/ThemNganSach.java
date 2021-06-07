package com.example.conspendemo;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import Adapter.GiaoDich;
import android.widget.Toast;

import java.sql.Date;

import Adapter.NganSach;
import DAO.MyDatabaseHelper;

public class ThemNganSach extends AppCompatActivity {
    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;
    private EditText editsotien;
    private EditText editngay1;
    private EditText editngay2;
    private Button btnsave;
    private Button btncancel;
    private Button back;

    private NganSach ns;
    private boolean needRefresh;
    private int mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ngan_sach);
        this.editsotien=(EditText)findViewById(R.id.edittextsotien);
        this.editngay1=(EditText)findViewById(R.id.edittextngay1);
        this.editngay2=(EditText)findViewById(R.id.edittextngay2);
        this.btnsave=(Button) findViewById(R.id.savebtn);
        this.btncancel=(Button) findViewById(R.id.cancelbtn);
        this.back = (Button)findViewById(R.id.xoabtn);

        btnsave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ClickSave();
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ClickCancel();
            }
        });
        Intent intent3 = this.getIntent();
        this.ns = (NganSach) intent3.getSerializableExtra("gd");
        if(ns==null){
            this.mode = MODE_CREATE;
        } else{
            this.mode = MODE_EDIT;
            this.editsotien.setText((int) ns.getTongtien());
            this.editngay1.setText((CharSequence) ns.getStartDate());
            this.editngay1.setText((CharSequence) ns.getEndDate());
        }
    }
    public void ClickSave(){
        MyDatabaseHelper db = new MyDatabaseHelper(this);

        Date ngaydau = Date.valueOf(this.editngay1.getText().toString());
        Date ngaycuoi = Date.valueOf(this.editngay2.getText().toString());
        Float sotien = Float.valueOf(this.editsotien.getText().toString());
        if(sotien.equals("")|| ngaydau.equals("")||ngaycuoi.equals("")){
            Toast.makeText(getApplicationContext()," Vui long nhap ten va noi dung",Toast.LENGTH_LONG).show();
            return;
        }
        if(mode == MODE_CREATE){
            this.ns = new NganSach(sotien,ngaydau,ngaycuoi);
            db.createNS(ns);

        }else{
            this.ns.setTongtien(sotien);
            this.ns.setStartDate(ngaydau);
            this.ns.setEndDate(ngaycuoi);
            db.updateNS(ns);
        }
        this.needRefresh=true;
        this.onBackPressed();
    }
    public void ClickCancel(){
        this.onBackPressed();
    }

}