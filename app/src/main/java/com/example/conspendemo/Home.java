package com.example.conspendemo;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    private Button logout;
    private Button themgd1;
    private Button themgd2;
    private Button tonghop;
    private Button ngansach;
    private Button theodoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView hellotext = findViewById(R.id.hellotext);
        TextView tientext = findViewById(R.id.tientext);
        themgd1 = findViewById(R.id.button6);
        tonghop = findViewById(R.id.button7);
        ngansach = findViewById(R.id.button8);
        theodoi = findViewById(R.id.button9);
        logout = findViewById(R.id.logout);
        Intent myIntent = getIntent();
        //tạo action cho các nút
        //nút thêm giao dịch
        themgd1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                  Intent intent1 = new Intent(Home.this,ThemGD.class);
                  Home.this.startActivity(intent1);
            }
        });

        //nút tổng hợp giao dịch
        tonghop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(Home.this,ShowGD.class);
                Home.this.startActivity(intent5);
            }
        });
        //nút lập ngân sách
        ngansach.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Home.this,ThemNganSach.class);
                Home.this.startActivity(intent3);
            }
        });
        //nút theo dõi số dư
        theodoi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(Home.this,Ngansach.class);
                Home.this.startActivity(intent4);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(Home.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}