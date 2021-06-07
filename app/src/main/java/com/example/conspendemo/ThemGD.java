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

import DAO.MyDatabaseHelper;

public class ThemGD extends AppCompatActivity {
    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;
    private EditText editname;
    private EditText editsotien;
    private EditText editngay;
    private Button btnsave;
    private Button btncancel;

    private GiaoDich gd;
    private boolean needRefresh;
    private int mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_gd);
        this.editname=(EditText)findViewById(R.id.edittext1);
        this.editngay=(EditText)findViewById(R.id.editTextDate);
        this.editsotien=(EditText)findViewById(R.id.editTextsotien);
        this.btnsave=(Button) findViewById(R.id.savebtn);
        this.btncancel=(Button) findViewById(R.id.cancelbtn);

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
        Intent intent1 = this.getIntent();
        this.gd = (GiaoDich) intent1.getSerializableExtra("gd");
        if(gd==null){
            this.mode = MODE_CREATE;
        } else{
            this.mode = MODE_EDIT;
            this.editname.setText(gd.getTengd());
            this.editngay.setText((CharSequence) gd.getNgaytao());
            this.editsotien.setText((int) gd.getSotien());
        }
    }
    public void ClickSave(){
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        String name = this.editname.getText().toString();
        Date ngaytao = Date.valueOf(this.editngay.getText().toString());
        Float sotien = Float.valueOf(this.editsotien.getText().toString());
        if(name.equals("")|| ngaytao.equals("")||sotien.equals("")){
            Toast.makeText(getApplicationContext()," Vui long nhap ten va noi dung",Toast.LENGTH_LONG).show();
            return;
        }
        if(mode == MODE_CREATE){
            this.gd = new GiaoDich(name,ngaytao,sotien);
            db.createGiaoDich(gd);

        }else{
            this.gd.setTengd(name);
            this.gd.setNgaytao(ngaytao);
            this.gd.setSotien(sotien);
            db.updateGiaoDich(gd);
        }
        this.needRefresh=true;
        this.onBackPressed();
    }
    public void ClickCancel(){
        this.onBackPressed();
    }
    public void finish(){
        Intent data = new Intent();
        data.putExtra("needRefresh",needRefresh);
        this.setResult(Activity.RESULT_OK,data);
        super.finish();
    }
}