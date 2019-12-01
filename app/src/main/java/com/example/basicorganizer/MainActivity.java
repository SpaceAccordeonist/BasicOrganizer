package com.example.basicorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText et_email, et_password;
    Button btn_register, btn_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){

                }
                else{

                }
            }
        };

        et_email = findViewById(R.id.edit_text_email);
        et_password = findViewById(R.id.edit_text_password);
        btn_register = findViewById(R.id.button_register);
        btn_signin = findViewById(R.id.button_signin);

        btn_signin.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btn_signin.getId()){
            if(!et_email.getText().toString().isEmpty() && !et_password.getText().toString().isEmpty()){
                signin(et_email.getText().toString(), et_password.getText().toString());
            }
            else{
                Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId() == btn_register.getId()){
            register(et_email.getText().toString(), et_password.getText().toString());
        }
    }

    public void signin(String login, String password){
        mAuth.signInWithEmailAndPassword(login,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Signing in failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void register(String login, String password){
        mAuth.createUserWithEmailAndPassword(login, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
