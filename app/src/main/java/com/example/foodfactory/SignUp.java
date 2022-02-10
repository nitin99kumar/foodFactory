package com.example.foodfactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText FullName, phone_number,Email_Address, passwordForReg;
    Button Register;
    FirebaseAuth Rauth;
    ProgressBar progBarr;
    TextView gotoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FullName = (EditText) findViewById(R.id.UserName);
        phone_number = (EditText) findViewById(R.id.Phone_number);
        Email_Address = (EditText) findViewById(R.id.Email_Address);
        passwordForReg = (EditText) findViewById(R.id.password);

        Register = (Button) findViewById(R.id.Register);
        Rauth = FirebaseAuth.getInstance();
        progBarr = (ProgressBar) findViewById(R.id.ProgBar);

        gotoLogin = (TextView) findViewById(R.id.gotoLogin);
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this, Login_Activity.class);
                startActivity(i);
            }
        });

        if(Rauth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), DashboardAfterLogin.class));
            finish();
        }
        else{
            Register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Email = Email_Address.getText().toString().trim();
                    String password = passwordForReg.getText().toString().trim();

                    if(TextUtils.isEmpty(Email)){
                        Email_Address.setError("Email is Required");
                        return;
                    }

                    if(TextUtils.isEmpty(password)){
                        passwordForReg.setError("Password is Required");
                        return;
                    }

                    if(passwordForReg.length() <= 6){
                        passwordForReg.setError("Password Length must be more then 6 Characters");
                        return;
                    }

                    progBarr.setVisibility(View.VISIBLE);

                    Rauth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUp.this, "User is Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),DashboardAfterLogin.class));
                            }
                            else{
                                Toast.makeText(SignUp.this,"Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            });

        }

    }
}