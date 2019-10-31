package com.example.sportsadda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener{

    EditText email;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);


        email = (EditText) findViewById(R.id.editText7);

        findViewById(R.id.sendMail).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        mAuth.signInWithEmailAndPassword(email.getText().toString(),"dummy").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){

                        try {
                            throw task.getException();
                        } catch (Exception e) {
                            Toast.makeText(ForgotPassword.this,"Email doesnot exits!",Toast.LENGTH_SHORT).show();
                        }
                        mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(ForgotPassword.this,"Password Reset Link Sent",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(ForgotPassword.this,LoginActivity.class));
                                }
                            }
                        });

                }

            }
        });
    }
}
