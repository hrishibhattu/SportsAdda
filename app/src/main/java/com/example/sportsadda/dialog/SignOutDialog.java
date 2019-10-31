//package com.example.sportsadda.dialog;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatDialogFragment;
//
//import com.example.sportsadda.LoginActivity;
//import com.google.firebase.auth.FirebaseAuth;
//
//public class SignOutDialog extends AppCompatDialogFragment {
//
//
//    private FirebaseAuth mAuth;
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Log out")
//                .setMessage("Are you sure?")
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                })
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                                mAuth = FirebaseAuth.getInstance();
//                                mAuth.signOut();
//                                Toast.makeText(SignOutDialog.this,"Sign Out Successful",Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(SignOutDialog.this, LoginActivity.class));
//                    }
//                });
//    }
//}
//
