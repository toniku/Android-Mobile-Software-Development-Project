/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.hokikoutsi2019.Classes.Game;
import com.example.hokikoutsi2019.Classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * Created by Janne Heikkilä, Toni Kukkohovi, Eetu Lehtomaa, Jouni Peltola
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart = null;
    RadioButton radioButtonHome = null;
    RadioButton radioButtonAway = null;
    EditText editTextOpponent = null;
    private int clickedNavItem = 0;
    private Button buttonLogOut;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private TextView textViewDrawHeader;
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    Log.i("LogInEvent", user.getFirstname());
                    Log.i("LogInEvent", user.getLastname());

                    String firstname = user.getFirstname();
                    String lastname = user.getLastname();

                    String capFirName = firstname.substring(0, 1).toUpperCase() + firstname.substring(1);
                    String capLasName = lastname.substring(0, 1).toUpperCase() + lastname.substring(1);

                    String fullname = capFirName + " " + capLasName;
                    textViewDrawHeader.setText(fullname);
                }
            } else {
                //textViewWarning.setText("Data fetching failed...");
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getApplicationContext().getString(R.string.new_game).toUpperCase());
        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);
        drawerLayout = findViewById(R.id.activity_main);
        navigationView = findViewById(R.id.nav_view);
        radioButtonHome = findViewById(R.id.radioButtonHome);
        radioButtonAway = findViewById(R.id.radioButtonAway);
        editTextOpponent = findViewById(R.id.editTextOpponent);
        setUpDrawer();
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        // getUser();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.buttonStart)) {

            if (radioButtonHome.isChecked()) {
                String opponent = editTextOpponent.getText().toString().toUpperCase();
                Game game = new Game("KIEKKO-LASER", opponent);
                Intent intent = new Intent(MainActivity.this, NewGameActivity.class);
                intent.putExtra("gameObject", game);
                startActivity(intent);
            } else {
                String opponent = editTextOpponent.getText().toString().toUpperCase();
                Game game = new Game(opponent, "KIEKKO-LASER");
                Intent i = new Intent(MainActivity.this, NewGameActivity.class);
                i.putExtra("gameObject", game);
                startActivity(i);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public void setUpDrawer() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close); //Remember to change string contents
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                switch (clickedNavItem) {
                    case R.id.drawer_logout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        break;
                    case R.id.drawer_line_edit:
                        startActivity(new Intent(getApplicationContext(), LineEditActivity.class));
                        break;
                    case R.id.drawer_lineup:
                        startActivity(new Intent(getApplicationContext(), LineupActivity.class));
                        break;
                    case R.id.drawer_games:
                        startActivity(new Intent(getApplicationContext(), LatestGamesActivity.class));
                        break;
                    case R.id.drawer_new_game:
                        break;
                }
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.drawer_logout:
                        clickedNavItem = R.id.drawer_logout;
                        break;
                    case R.id.drawer_line_edit:
                        clickedNavItem = R.id.drawer_line_edit;
                        break;
                    case R.id.drawer_lineup:
                        clickedNavItem = R.id.drawer_lineup;
                        break;
                    case R.id.drawer_games:
                        clickedNavItem = R.id.drawer_games;
                        break;
                    case R.id.drawer_new_game:
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);
        textViewDrawHeader = headerView.findViewById(R.id.drawer_header);
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    Log.i("LOL", "Logged In as " + mAuth.getCurrentUser().getEmail());
                    Log.i("LOL", "Logged In as " + mAuth.getCurrentUser().getUid());
                } else {
                    Log.i("LOL", "No user found...");
                }
            }
        };
    }


    public void getUser() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        Query query = databaseReference.orderByChild("email").equalTo(mAuth.getCurrentUser().getEmail());
        query.addValueEventListener(valueEventListener);
    }

    @Override
    public void onBackPressed() {
        // Disables back buttonStart
    }
}
