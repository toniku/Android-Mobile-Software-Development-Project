/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import android.widget.Toast;

import com.example.hokikoutsi2019.Classes.LineupAdapter;
import com.example.hokikoutsi2019.Classes.LineupPlayer;
import com.example.hokikoutsi2019.Classes.LineupPlayerAdapter;
import com.example.hokikoutsi2019.Classes.Player;
import com.example.hokikoutsi2019.Classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LineupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogOut;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private TextView textViewDrawHeader;
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
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
            } else {
                //textViewWarning.setText("Data fetching failed...");
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    private LineupPlayerAdapter lineupPlayerAdapter;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference databaseReference;
    private View currentView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineup);
        setUpDrawer();
        final ListView listView = findViewById(R.id.playerListView);
        lineupPlayerAdapter = new LineupPlayerAdapter(this, R.layout.lineup_player_list_item);
        listView.setAdapter(lineupPlayerAdapter);
        addPlayers();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LineupActivity.this, PlayerCardActivity.class);
                Player player = (Player) listView.getAdapter().getItem(position);
                intent.putExtra("PlayerName",listView.getAdapter().getItemId(position));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public void setUpDrawer() {
        dl = findViewById(R.id.activity_lineup);
        t = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close); //Remember to change string contents
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                Log.i("LOL", "Item id: " + id);

                if (id == R.id.drawer_logout) {
                    Log.i("LOL", "Log out pressed");
                    mAuth.getInstance().signOut();
                    Intent intent = new Intent(LineupActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if (id == R.id.drawer_lineup) {
                    Intent intent = new Intent(LineupActivity.this, LineupActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if (id == R.id.drawer_games)
                {
                    Intent intent = new Intent(LineupActivity.this, LatestGamesActivity.class);
                    startActivity(intent);
                    return true;
                }
                return true;
            }
        });

        View headerView = nv.inflateHeaderView(R.layout.nav_header);
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
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        Query query = databaseReference.orderByChild("email").equalTo(mAuth.getCurrentUser().getEmail());
        query.addValueEventListener(valueEventListener);
    }

    private void addPlayers() {
        LineupPlayer lineupPlayer = new LineupPlayer("Jani", "Hakanpää", 94);
        LineupPlayer lineupPlayer1 = new LineupPlayer("Shaun", "Heshka", 26);
        LineupPlayer lineupPlayer2 = new LineupPlayer("Teemu", "Kivihalme", 61);
        LineupPlayer lineupPlayer3 = new LineupPlayer("Lasse", "Kukkonen", 5);
        LineupPlayer lineupPlayer4 = new LineupPlayer("Atte", "Ohtamaa", 55);
        LineupPlayer lineupPlayer5 = new LineupPlayer("Aleksi", "Heponiemi", 20);
        LineupPlayer lineupPlayer6 = new LineupPlayer("Jussi", "Jokinen", 36);
        LineupPlayer lineupPlayer7 = new LineupPlayer("Rasmus", "Kupari", 19);
        LineupPlayer lineupPlayer8 = new LineupPlayer("Ville", "Leskinen", 12);
        LineupPlayer lineupPlayer9 = new LineupPlayer("Oskar", "Osala", 92);
        LineupPlayer lineupPlayer10 = new LineupPlayer("Mika", "Pyörälä", 17);
        LineupPlayer lineupPlayer11 = new LineupPlayer("Jari", "Sailio", 41);
        LineupPlayer lineupPlayer12 = new LineupPlayer("Tino", "Metsävainio", 25);
        LineupPlayer lineupPlayer13 = new LineupPlayer("Jasper", "Lindsten", 67);
        LineupPlayer lineupPlayer14 = new LineupPlayer("Nicklas", "Lasu", 31);
        LineupPlayer lineupPlayer15 = new LineupPlayer("Michal", "Kristof", 13);
        LineupPlayer lineupPlayer16 = new LineupPlayer("Radek", "Koblizek", 29);
        LineupPlayer lineupPlayer17 = new LineupPlayer("Otto", "Karvinen", 21);
        LineupPlayer lineupPlayer18 = new LineupPlayer("Miska", "Humaloja", 27);
        LineupPlayer lineupPlayer19 = new LineupPlayer("Sami", "Anttila", 18);
        LineupPlayer lineupPlayer20 = new LineupPlayer("Taneli", "Ronkainen", 22);
        lineupPlayerAdapter.add(lineupPlayer);
        lineupPlayerAdapter.add(lineupPlayer1);
        lineupPlayerAdapter.add(lineupPlayer2);
        lineupPlayerAdapter.add(lineupPlayer3);
        lineupPlayerAdapter.add(lineupPlayer4);
        lineupPlayerAdapter.add(lineupPlayer5);
        lineupPlayerAdapter.add(lineupPlayer6);
        lineupPlayerAdapter.add(lineupPlayer7);
        lineupPlayerAdapter.add(lineupPlayer8);
        lineupPlayerAdapter.add(lineupPlayer9);
        lineupPlayerAdapter.add(lineupPlayer10);
        lineupPlayerAdapter.add(lineupPlayer11);
        lineupPlayerAdapter.add(lineupPlayer12);
        lineupPlayerAdapter.add(lineupPlayer13);
        lineupPlayerAdapter.add(lineupPlayer14);
        lineupPlayerAdapter.add(lineupPlayer15);
        lineupPlayerAdapter.add(lineupPlayer16);
        lineupPlayerAdapter.add(lineupPlayer17);
        lineupPlayerAdapter.add(lineupPlayer18);
        lineupPlayerAdapter.add(lineupPlayer19);
        lineupPlayerAdapter.add(lineupPlayer20);
    }
}
