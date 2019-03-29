/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hokikoutsi2019.Classes.ListViewSubTrainingsAdapter;
import com.example.hokikoutsi2019.Classes.SubTraining;
import com.example.hokikoutsi2019.Classes.Training;
import com.example.hokikoutsi2019.Classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowTrainingListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    SubTraining subTraining;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private TextView textViewWarning;
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
                textViewWarning.setText("Data fetching failed...");
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    private ArrayList<SubTraining> arrayList;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_training_list);

        dl = findViewById(R.id.activity_main);
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

                if (id == R.id.drawer_account) {
                    Intent intent = new Intent(ShowTrainingListActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.drawer_training) {
                    Intent intent = new Intent(ShowTrainingListActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.drawer_logout) {
                    Log.i("LOL", "Log out pressed");
                    mAuth.getInstance().signOut();
                    Intent intent = new Intent(ShowTrainingListActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.drawer_calendar) {
                    Toast.makeText(ShowTrainingListActivity.this, "Calendar", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.drawer_teams) {
                    Toast.makeText(ShowTrainingListActivity.this, "My Teams", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    return true;
                }

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

        Intent i = getIntent();
        Training training = (Training) i.getSerializableExtra("Training");
        Log.i("ASD", training.getName());
        Log.i("ASD", "Subtrainings Size: " + training.getSubTrainingsSize());
        arrayList = training.getSubTrainingsArrayList();

        ListView listView = findViewById(R.id.listViewSubTrainings);
        listView.setOnItemClickListener(this);
        listView.setAdapter(new ListViewSubTrainingsAdapter(this, arrayList));

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

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try {

            Log.i("LOL", "Itemiä klikattu");
            Log.i("LOL", " " + i);

            subTraining = arrayList.get(i);
            Log.i("LOL", subTraining.getName());
            showDialog();
        } catch (Exception e) {
            Log.i("LOL", e.getMessage().toString());
        }
    }


    void showDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment newFragment = SubTrainingInfoDialog.newInstance(1, subTraining);
        newFragment.show(ft, "yourTag");
    }
}
