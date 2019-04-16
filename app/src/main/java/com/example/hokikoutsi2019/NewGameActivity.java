/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.hokikoutsi2019.Classes.Game;
import com.example.hokikoutsi2019.Classes.NewGameFragmentPagerAdapter;
import com.example.hokikoutsi2019.Classes.NewGamePageFragment;


public class NewGameActivity extends AppCompatActivity {

    TextView textViewHome = null;
    TextView textViewAway = null;
    NewGameFragmentPagerAdapter newGameFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        ViewPager viewPager = findViewById(R.id.viewpager);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        Intent i = getIntent();
        Game game = (Game) i.getSerializableExtra("gameObject");
        Log.d("LOL", "NewGame: " + game.getHomeTeam() + " VS " + game.getAwayTeam());

        newGameFragmentPagerAdapter = new NewGameFragmentPagerAdapter(getSupportFragmentManager(), NewGameActivity.this, game);
        viewPager.setAdapter(newGameFragmentPagerAdapter);


        //Add contents to fragment
        try
        {

        }
        catch (Exception e)
        {
            Log.d("LOL", e.toString());
        }




    }
}
