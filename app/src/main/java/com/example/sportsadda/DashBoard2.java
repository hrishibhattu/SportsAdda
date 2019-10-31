package com.example.sportsadda;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class DashBoard2 extends AppCompatActivity {
    private ImageView booking,scanner,addVenue;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_dashboard2);

        //Setting imageview
        addVenue =(ImageView)findViewById(R.id.addVenue);
        addVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard2.this,AddVenue.class));
            }
        });

        //Scanner
        scanner = (ImageView)findViewById(R.id.scanner);
        final Activity activity = this;
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.google.zxing.integration.android.IntentIntegrator integrator = new com.google.zxing.integration.android.IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("SCAN");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.initiateScan();
            }
        });

        booking = (ImageView)findViewById(R.id.bookme);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard2.this,BookingActivity.class)); //Booked- > BookingActivity
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
            }
        }
        else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void signOut(){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        Toast.makeText(DashBoard2.this,"Sign Out Successful",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(DashBoard2.this,LoginActivity.class));
//          openDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.signout :
                signOut();
                break;
            case R.id.switchDash:
                Toast.makeText(DashBoard2.this,"Switching Dashboard",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashBoard2.this,DashBoard.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
