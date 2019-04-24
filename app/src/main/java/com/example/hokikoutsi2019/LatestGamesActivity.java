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

import com.example.hokikoutsi2019.Classes.Game;
import com.example.hokikoutsi2019.Classes.GameAdapter;
import com.example.hokikoutsi2019.Classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LatestGamesActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TextView textViewDrawHeader;

    private ValueEventListener valueEventListener = new ValueEventListener() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_games);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getApplicationContext().getString(R.string.latest_games).toUpperCase());
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

        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    private void setUpDrawer() {
        drawerLayout = findViewById(R.id.activity_latestgames);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close); //Remember to change string contents
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.drawer_logout) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(LatestGamesActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.drawer_line_edit) {
                    Intent intent = new Intent(LatestGamesActivity.this, LineEditActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.drawer_lineup) {
                    Intent intent = new Intent(LatestGamesActivity.this, LineupActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.drawer_new_game) {
                    Intent intent = new Intent(LatestGamesActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                }
                finish();
                drawerLayout.closeDrawers();
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


    public void getUser() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        Query query = databaseReference.orderByChild("email").equalTo(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail());
        query.addValueEventListener(valueEventListener);
    }

    private void setUpListView() {
        final ListView listView = findViewById(R.id.gamesListView);
        GameAdapter adapter = new GameAdapter(this, R.layout.game_list_item);

        //ADD TEST DATA
        Game game1 = new Game("KÄRPÄT", "KIEKKO-LASER");
        game1.setHomeGoal("KUKKONEN");
        game1.setAwayGoal("LESKINEN");
        game1.setHomeGoal("HAKANPÄÄ");
        game1.setHomeGoal("HEPONIEMI");
        game1.setAwayGoal("KUPARI");

        adapter.add(game1);
        Game game2 = new Game("KÄRPÄT", "HPK");
        adapter.add(game2);
        Game game3 = new Game("TAPPARA", "KÄRPÄT");
        adapter.add(game3);
        Game game4 = new Game("HIFK", "KÄRPÄT");
        adapter.add(game4);
        Game game5 = new Game("KÄRPÄT", "PELICANS");
        adapter.add(game5);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Game game = (Game) parent.getAdapter().getItem(position);
                Log.d("LOL", game.getHomeTeam() + " VS " + game.getAwayTeam());

                Intent i = new Intent(LatestGamesActivity.this, GameReportActivity.class);
                i.putExtra("gameObject", game);
                startActivity(i);
            }
        });
    }
}

/*


 *//*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *//*

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
        import java.util.Objects;

public class LineupActivity extends AppCompatActivity implements View.OnClickListener {

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
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private LineupPlayerAdapter lineupPlayerAdapter;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineup);
        drawerLayout = findViewById(R.id.activity_lineup);
        navigationView = findViewById(R.id.nav_view);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getApplicationContext().getString(R.string.lineup).toUpperCase());
        setUpDrawer();
        final ListView listView = findViewById(R.id.playerListView);
        addPlayers();
        lineupPlayerAdapter = new LineupPlayerAdapter(this, playerList);
        listView.setAdapter(lineupPlayerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Player player2 = (Player) parent.getAdapter().getItem(position);
                Log.d("LOL", "Clicked player: " + player2.getFirstname() + " " + player2.getLastname());
                Intent i = new Intent(LineupActivity.this, PlayerCardActivity.class);
                i.putExtra("playerObject", player2);
                startActivity(i);
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

        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public void setUpDrawer() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close); //Remember to change string contents
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                Log.i("LOL", "Item id: " + id);

                if (id == R.id.drawer_logout) {
                    Log.i("LOL", "Log out pressed");
                    mAuth.getInstance().signOut();
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(LineupActivity.this, LoginActivity.class);
                    startActivity(intent);

                } else if (id == R.id.drawer_line_edit) {
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(LineupActivity.this, LineEditActivity.class);
                    startActivity(intent);


                } else if (id == R.id.drawer_lineup) {
                    drawerLayout.closeDrawers();

                } else if (id == R.id.drawer_games) {
                    drawerLayout.closeDrawers();

                    Intent intent = new Intent(LineupActivity.this, LatestGamesActivity.class);
                    startActivity(intent);

                } else if (id == R.id.drawer_new_game) {
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(LineupActivity.this, MainActivity.class);
                    startActivity(intent);

                }
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
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        Query query = databaseReference.orderByChild("email").equalTo(mAuth.getCurrentUser().getEmail());
        query.addValueEventListener(valueEventListener);
    }

    private void addPlayers() {
        Player lineupPlayer = new Player("Jani", "Hakanpää", 94);
        playerList.add(lineupPlayer);

        Player lineupPlayer1 = new Player("Toni", "Kukkohovi", 69);
        playerList.add(lineupPlayer1);

        Player lineupPlayer2 = new Player("Eetu", "Lehtomaa", 96);
        lineupPlayer2.setContact("Liisantie 7", "90560", "Oulu", "0407193427");
        lineupPlayer2.setStats(2019);
        playerList.add(lineupPlayer2);

        Player lineupPlayer3 = new Player("Jouni", "Peltola", 85);
        playerList.add(lineupPlayer3);

    }
}*/

