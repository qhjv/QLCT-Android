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
            emailEt.setError("H??y nh???p email!!!");
            return;
        }
        else if(TextUtils.isEmpty(password1)){
            passwordEt1.setError("H??y nh???p m???t kh???u !!!");
            return;
        }
        else if(TextUtils.isEmpty(password2)){
            passwordEt2.setError("H??y nh???p l???i m???t kh???u !!!");
            return;
        }
        else if(!password1.equals(password2)){
            passwordEt2.setError("M???t kh???u ch??a kh???p !!!");
            return;
        }
        else if(password1.length()<4){
            passwordEt1.setError("M???t kh???u ph???i tr??n 4 k?? t???!!!");
            return;
        }
        else if(!isVallidEmail(email)){
            emailEt.setError("Email ch??a ????ng!!!");
            return;
        }
        progressDialog.setMessage("H??y ch???...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Signup.this,"????ng k?? th??nh c??ng",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Signup.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Signup.this,"????ng k?? th???t b???i",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    private  boolean isVallidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}