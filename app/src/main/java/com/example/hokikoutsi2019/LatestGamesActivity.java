/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hokikoutsi2019.Classes.Game;
import com.example.hokikoutsi2019.Classes.GameAdapter;
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

import java.util.ArrayList;

public class LatestGamesActivity extends AppCompatActivity implements View.OnClickListener {

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
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_games);
        setUpDrawer();
        setUpListView();
        // getUser();
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
        dl = findViewById(R.id.activity_lastestgames);
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
                    Intent intent = new Intent(LatestGamesActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if (id == R.id.drawer_lineup)
                {
                    Intent intent = new Intent(LatestGamesActivity.this, LineupActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if (id == R.id.drawer_games)
                {
                    Intent intent = new Intent(LatestGamesActivity.this, LatestGamesActivity.class);
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

    public void setUpListView()
    {
        final ListView listView = findViewById(R.id.gamesListView);
        GameAdapter adapter = new GameAdapter(this, R.layout.game_list_item);

        //ADD TEST DATA
        Game game1 = new Game("KIEKKO-LASER", "KÄRPÄT", 1, 2);
        adapter.add(game1);
        Game game2 = new Game("KIEKKO-LASER", "LIMINGAN KIEKKO", 4, 5);
        adapter.add(game2);
        Game game3 = new Game("TAPPARA", "KIEKKO-LASER", 0, 2);
        adapter.add(game3);
        Game game4 = new Game("ROKI", "KIEKKO-LASER", 1, 3);
        adapter.add(game4);
        Game game5 = new Game("KIEKKO-LASER", "KEPA", 2, 2);
        adapter.add(game5);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

}
