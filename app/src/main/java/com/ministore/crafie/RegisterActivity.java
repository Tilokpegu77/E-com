package com.ministore.crafie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;
    private Button mRegbtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mName=findViewById(R.id.reg_Name);
        mEmail=findViewById(R.id.reg_Email);
        mPassword=findViewById(R.id.reg_Password);
        mRegbtn=findViewById(R.id.reg_btn);
        mAuth=FirebaseAuth.getInstance();

        mRegbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=mName.getText().toString();
                String Email=mEmail.getText().toString();
                String Password=mPassword.getText().toString();
                if(!Name.isEmpty() && !Email.isEmpty() && !Password.isEmpty()) {
                    mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Account Succesfully Created", Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);
                               startActivity(intent);
                        }else{
                            Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                        }
                    });
                }else{
                    Toast.makeText(RegisterActivity.this, "Please fill the empty field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signIn(View view) {
        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}