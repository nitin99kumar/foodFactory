package com.example.foodfactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {

    TextView goToRegister;

    EditText Loginid, Password;
    Button loginButton;
    FirebaseAuth LAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        goToRegister = (TextView) findViewById(R.id.goToRegister);

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, SignUp.class);
                startActivity(intent);
            }
        });

        Loginid = (EditText) findViewById(R.id.loginId);
        Password = (EditText) findViewById(R.id.Password);
        loginButton = (Button) findViewById(R.id.LoginButton);
        LAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Loginid.getText().toString().trim();
                String pass = Password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Loginid.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    Password.setError("Password is Required");
                    return;
                }

                if(pass.length() < 6){
                    Password.setError("Password must be more than 6 Characters");
                    return;
                }

                LAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login_Activity.this, "Logged-In Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),DashboardAfterLogin.class));
                        }
                        else{
                            Toast.makeText(Login_Activity.this,"Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}