package com.example.conspendemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.ListNganSach;
import DAO.NganSachDB;
import Adapter.NganSach;

public class ThemNganSach extends AppCompatActivity implements View.OnClickListener {
    private EditText txt1, txt2;
    private RecyclerView re;
    private ListNganSach adapter;
    private Button btn,btn1;
    private NganSachDB db;
    private ArrayList<NganSach> ar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ngan_sach);
        txt1=findViewById(R.id.muchi);
        txt2=findViewById(R.id.thoigian);
        re=findViewById(R.id.res);
        btn=findViewById(R.id.btn);
        btn1=findViewById(R.id.btn1);
        db=new NganSachDB(this);
        btn.setOnClickListener(this);
        Intent myIntent = getIntent();
        btn1.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(ThemNganSach.this, Ngansach.class);

                ThemNganSach.this.startActivity(myIntent);
            }
        });

        ar=new ArrayList<>();
        ar=db.getTK();

        re.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ListNganSach(ar, this);
        re.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn){
            NganSach a=new NganSach(Long.parseLong(txt1.getText().toString()),txt2.getText().toString());
            ar.add(a);
            adapter.notifyDataSetChanged();
            if(db.themTK(a)!=0){
                Toast.makeText(this, "Thanh cong", Toast.LENGTH_LONG).show();
            }
        }
    }
}