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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hokikoutsi2019.Classes.LineupPlayerAdapter;
import com.example.hokikoutsi2019.Classes.Player;
import com.example.hokikoutsi2019.Classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class LineupActivity extends AppCompatActivity implements View.OnClickListener {


    private int clickedNavItem = 0;
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
    private ArrayList<Player> playerList = new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public LineupActivity() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineup);
        drawerLayout = findViewById(R.id.activity_lineup);
        navigationView = findViewById(R.id.nav_view);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getApplicationContext().getString(R.string.lineup).toUpperCase());
        setUpDrawer();
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        final ListView listView = findViewById(R.id.playerListView);
        addPlayers();
        LineupPlayerAdapter lineupPlayerAdapter = new LineupPlayerAdapter(this, playerList);
        listView.setAdapter(lineupPlayerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Player player2 = (Player) parent.getAdapter().getItem(position);
                Intent intent = new Intent(LineupActivity.this, PlayerCardActivity.class);
                intent.putExtra("playerObject", player2);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onClick(View view) {

    }

    public void setUpDrawer() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close); //Remember to change string contents
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
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
                        break;
                    case R.id.drawer_games:
                        startActivity(new Intent(getApplicationContext(), LatestGamesActivity.class));
                        break;
                    case R.id.drawer_new_game:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
                        break;
                    case R.id.drawer_games:
                        clickedNavItem = R.id.drawer_games;
                        break;
                    case R.id.drawer_new_game:
                        clickedNavItem = R.id.drawer_new_game;
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
                    Log.i("LogInEvent", "Logged In as " + mAuth.getCurrentUser().getEmail());
                    Log.i("LogInEvent", "Logged In as " + mAuth.getCurrentUser().getUid());
                } else {
                    Log.i("LogInEvent", "No user found...");
                }
            }
        };
    }

    private void addPlayers() {
        Player lineupPlayer = new Player("Aleksi", "Heponiemi", 20);
        lineupPlayer.setContact("Kotkantie 1", "90250", "Oulu", "0407193427");
        lineupPlayer.setStats(2019);
        playerList.add(lineupPlayer);

        Player lineupPlayer1 = new Player("Aleksi", "Mäkelä", 37);
        playerList.add(lineupPlayer1);

        Player lineupPlayer2 = new Player("Atte", "Ohtamaa", 55);
        playerList.add(lineupPlayer2);

        Player lineupPlayer3 = new Player("Jani", "Hakanpää", 58);
        playerList.add(lineupPlayer3);

        Player lineupPlayer4 = new Player("Jari", "Sailio", 41);
        playerList.add(lineupPlayer4);

        Player lineupPlayer5 = new Player("Jussi", "Jokinen", 36);
        playerList.add(lineupPlayer5);

        Player lineupPlayer6 = new Player("Jussi", "Rynnäs", 40);
        playerList.add(lineupPlayer6);

        Player lineupPlayer7 = new Player("Lasse", "Kukkonen", 5);
        playerList.add(lineupPlayer7);

        Player lineupPlayer8 = new Player("Mika", "Pyörälä", 17);
        playerList.add(lineupPlayer8);

        Player lineupPlayer9 = new Player("Mikko", "Niemelä", 28);
        playerList.add(lineupPlayer9);

        Player lineupPlayer10 = new Player("Miska", "Humaloja", 27);
        playerList.add(lineupPlayer10);

        Player lineupPlayer11 = new Player("Nicklas", "Lasu", 31);
        playerList.add(lineupPlayer11);

        Player lineupPlayer12 = new Player("Oskar", "Osala", 92);
        playerList.add(lineupPlayer12);

        Player lineupPlayer13 = new Player("Otto", "Karvinen", 21);
        playerList.add(lineupPlayer13);

        Player lineupPlayer14 = new Player("Rasmus", "Kupari", 19);
        playerList.add(lineupPlayer14);

        Player lineupPlayer15 = new Player("Taneli", "Ronkainen", 22);
        playerList.add(lineupPlayer15);

        Player lineupPlayer16 = new Player("Teemu", "Kivihalme", 61);
        playerList.add(lineupPlayer16);

        Player lineupPlayer17 = new Player("Veini", "Vehviläinen", 35);
        playerList.add(lineupPlayer17);


        Player lineupPlayer18 = new Player("Ville", "Leskinen", 12);
        playerList.add(lineupPlayer18);

    }
}
