package com.example.conspendemo;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    private EditText emailEt,passwordEt1,passwordEt2;
    private Button SignUpButton;
    private TextView SignInTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth=FirebaseAuth.getInstance();
        emailEt=findViewById(R.id.email);
        passwordEt1=findViewById(R.id.password1);
        passwordEt2=findViewById(R.id.password2);
        SignUpButton=findViewById(R.id.register);
        progressDialog=new ProgressDialog(this);
        SignInTv=findViewById(R.id.signInTv);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        SignInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void Register(){
        String email=emailEt.getText().toString();
        String password1=passwordEt1.getText().toString();
        String password2=passwordEt2.getText().toString();
        if(TextUtils.isEmpty(email)){
            emailEt.setError("Hãy nhập email!!!");
            return;
        }
        else if(TextUtils.isEmpty(password1)){
            passwordEt1.setError("Hãy nhập mật khẩu !!!");
            return;
        }
        else if(TextUtils.isEmpty(password2)){
            passwordEt2.setError("Hãy nhập lại mật khẩu !!!");
            return;
        }
        else if(!password1.equals(password2)){
            passwordEt2.setError("Mật khẩu chưa khớp !!!");
            return;
        }
        else if(password1.length()<4){
            passwordEt1.setError("Mật khẩu phải trên 4 ký tự!!!");
            return;
        }
        else if(!isVallidEmail(email)){
            emailEt.setError("Email chưa đúng!!!");
            return;
        }
        progressDialog.setMessage("Hãy chờ...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Signup.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Signup.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Signup.this,"Đăng ký thất bại",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    private  boolean isVallidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}