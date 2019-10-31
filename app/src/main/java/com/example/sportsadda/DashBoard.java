package com.example.sportsadda;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.sportsadda.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashBoard extends AppCompatActivity implements View.OnClickListener {

    private TextView name;
    private TextView email;
    private TextView username;

    private Button myBooking;
    private Button venue;
    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;
    private User user;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mAuth = FirebaseAuth.getInstance();
//
//        name = (TextView) findViewById(R.id.name);
//        email = (TextView) findViewById(R.id.email);
//        username = (TextView) findViewById(R.id.username);
//
//        mAuth = FirebaseAuth.getInstance();
//        String uId = mAuth.getCurrentUser().getUid();
//        mUserRef = FirebaseDatabase.getInstance().getReference("users/"+uId);
//
//        mUserRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                user = dataSnapshot.getValue(User.class);
//                name.append(user.name);
//                username.append(user.username);
//                email.append(user.email);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });

        //CardView onClickListener
        findViewById(R.id.viewVenue).setOnClickListener(this);
        findViewById(R.id.viewBookings).setOnClickListener(this);
        findViewById(R.id.viewSettings).setOnClickListener(this);
        findViewById(R.id.viewMe).setOnClickListener(this);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        ImageAdapter adapterView = new ImageAdapter(this);
        viewPager.setAdapter(adapterView);



    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if(i == R.id.viewVenue){
            startActivity(new Intent(DashBoard.this,VenueActivity.class));
        }else if (i == R.id.viewBookings){
            startActivity(new Intent(DashBoard.this,Booked.class));  //BookingActivity ->Booked
        }else if (i == R.id.viewSettings){
            startActivity(new Intent(DashBoard.this,SettingActivity.class));
        }else if(i == R.id.viewMe){
            startActivity(new Intent(DashBoard.this,MeActivity.class));
        }
    }

    private void signOut(){
        mAuth.signOut();
        Toast.makeText(DashBoard.this,"Sign Out Successful",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(DashBoard.this,LoginActivity.class));
//          openDialog();
    }

//    private void openDialog(){
//
//    }

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
                Toast.makeText(DashBoard.this,"Switching Dashboard",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashBoard.this,DashBoard2.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
