package com.example.sportsadda;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.sportsadda.models.User;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    EditText nameField,userField,emailField,passField,repassField;
    private DatabaseReference myRef;
    Button signup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("users");

        //Views
        nameField = (EditText) findViewById(R.id.editText1);
        userField = (EditText) findViewById(R.id.editText2);
        emailField = (EditText) findViewById(R.id.editText3);
        passField = (EditText) findViewById(R.id.editText4);
        repassField = (EditText) findViewById(R.id.editText5);

        //Buttons
        findViewById(R.id.SignMeUp).setOnClickListener(this);



    }


    private void signUp(){
        if(TextUtils.isEmpty(nameField.getText()) || TextUtils.isEmpty(userField.getText()) || TextUtils.isEmpty(emailField.getText()) ||
                TextUtils.isEmpty(passField.getText()) || TextUtils.isEmpty(repassField.getText())){
            if(TextUtils.isEmpty(nameField.getText()))
                nameField.setError("");
            if(TextUtils.isEmpty(userField.getText()))
                nameField.setError("");
            if(TextUtils.isEmpty(passField.getText()))
                nameField.setError("");
            if(TextUtils.isEmpty(emailField.getText()))
                nameField.setError("");
            if(TextUtils.isEmpty(repassField.getText()))
                nameField.setError("");
            return;
        }

        String pass = passField.getText().toString();
        String repass = repassField.getText().toString();

        if(!repass.matches(pass)){
            repassField.setError("Password doesnot match");
            return;
        }

        mAuth.createUserWithEmailAndPassword(emailField.getText().toString(),passField.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    onAuthSuccess(task.getResult().getUser());
                }
                    else{
                    Toast.makeText(SignUpActivity.this,"SignUp Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onAuthSuccess(FirebaseUser user){
        String username =  userField.getText().toString();
        String name = nameField.getText().toString();

        writeNewUser(user.getUid(),name,username,user.getEmail());
        startActivity(new Intent(SignUpActivity.this,DashBoard.class));
    }


    private void writeNewUser(String userId,String name,String username,String email){
        User user = new User(name,username,email);
        myRef.child(userId).setValue(user);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if(i == R.id.SignMeUp){
           signUp();
        }
    }
}
