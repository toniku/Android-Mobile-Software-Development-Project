package com.example.hokikoutsi2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hokikoutsi2019.Classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonEditUserInfo;

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private TextView textViewDrawHeader;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        buttonEditUserInfo = findViewById(R.id.buttonEditUserInfo);
        buttonEditUserInfo.setOnClickListener(this);

        dl = (DrawerLayout)findViewById(R.id.activity_profile);
        t = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close); //Remember to change string contents
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView)findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                Log.i("LOL", "Item id: " + id);

                if (id == R.id.drawer_account) {
                    Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if (id == R.id.drawer_training)
                {
                    Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if (id == R.id.drawer_logout) {
                    Log.i("LOL", "Log out pressed");
                    mAuth.getInstance().signOut();
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if (id == R.id.drawer_calendar)
                {
                    Toast.makeText(ProfileActivity.this, "Calendar", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if (id == R.id.drawer_teams)
                {
                    Intent intent = new Intent(ProfileActivity.this, TeamsActivity.class);
                    startActivity(intent);
                    return true;
                }
                else
                {
                    return true;
                }

            }

        });

        View headerView = nv.inflateHeaderView(R.layout.nav_header);
        textViewDrawHeader = (TextView) headerView.findViewById(R.id.drawer_header);
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null)
                {
                    Log.i("LOL", "Logged In as " + mAuth.getCurrentUser().getEmail());
                    Log.i("LOL", "Logged In as " + mAuth.getCurrentUser().getUid());
                }
                else
                {
                    Log.i("LOL", "No user found...");
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthStateListener);
        //Get User Data
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        Query query = databaseReference.orderByChild("email").equalTo(mAuth.getCurrentUser().getEmail());
        query.addValueEventListener(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists())
            {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    User user = snapshot.getValue(User.class);
                    Log.i("LOL", user.getFirstname());
                    Log.i("LOL", user.getLastname());

                    String firstname = user.getFirstname();
                    String lastname = user.getLastname();

                    String capFirName = firstname.substring(0, 1).toUpperCase() + firstname.substring(1);
                    String capLasName = lastname.substring(0, 1).toUpperCase() + lastname.substring(1);

                    String fullname = capFirName + " " + capLasName;
                    textViewDrawHeader.setText(fullname);
                }
            }
            else
            {

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    @Override
    public void onClick(View view) {


        if (view == findViewById(R.id.buttonEditUserInfo))
        {
            //Toast.makeText(ProfileActivity.this, "Button Clicked!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProfileActivity.this, EditUserInfoActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Disables back button
    }
}
