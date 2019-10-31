package com.example.sportsadda;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth mAuth;
    EditText emailField,passField;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        //Views
        emailField = (EditText) findViewById(R.id.editText);
        passField = (EditText) findViewById(R.id.editText6);

        //Buttons
        findViewById(R.id.logMeIn).setOnClickListener(this);

        //Radio Group and Button
        radioGroup = (RadioGroup)findViewById(R.id.radioG);


        //Onclick to views
        findViewById(R.id.forgotpassword).setOnClickListener(this);
        findViewById(R.id.newuser).setOnClickListener(this);
    }





    public static boolean isValidEmail(String target) {

        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(radioId);
    }


    private void signIn(){
        String email = emailField.getText().toString();
        String pass = passField.getText().toString();
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(radioId);
        final String who =  radioButton.getText().toString();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
            if(TextUtils.isEmpty(email))
                emailField.setError("This field cannot be empty");
            if(TextUtils.isEmpty(pass))
                passField.setError("This field can't be empty");
            return;
        }

        if(!isValidEmail(email)) {
            emailField.setError("Invalid email");
            return;
        }


        mAuth.signInWithEmailAndPassword(emailField.getText().toString(),passField.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Welcome " + who, Toast.LENGTH_SHORT).show();
                    if (who.matches("User")) {
                        startActivity(new Intent(LoginActivity.this, DashBoard.class));
                    }else if(who.matches("Venue Owner")){
                        startActivity(new Intent(LoginActivity.this,DashBoard2.class));
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this,"Authentication Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    //Things to happen on click
    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.logMeIn) {
            signIn();
        } else if (i == R.id.forgotpassword) {
            startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
        } else if (i == R.id.newuser) {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(LoginActivity.this,DashBoard.class));
        }
    }
}
