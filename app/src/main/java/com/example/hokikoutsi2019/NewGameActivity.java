/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.hokikoutsi2019.Classes.Game;
import com.example.hokikoutsi2019.Classes.GameReportFragment;
import com.example.hokikoutsi2019.Classes.NewGameFragmentPagerAdapter;
import com.example.hokikoutsi2019.Classes.NewGamePageFragment;


public class NewGameActivity extends AppCompatActivity {

    private NewGameFragmentPagerAdapter newGameFragmentPagerAdapter;
    private ViewPager viewPager;
    private NewGamePageFragment fragment1;
    private GameReportFragment fragment2;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        Intent i = getIntent();
        game = (Game) i.getSerializableExtra("gameObject");

        newGameFragmentPagerAdapter = new NewGameFragmentPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewpager);
        setUpViewPager(viewPager);
        fragment1.getGame(game);
    }

    private void setUpViewPager(ViewPager viewPager) {
        NewGameFragmentPagerAdapter adapter = new NewGameFragmentPagerAdapter(getSupportFragmentManager());
        fragment1 = new NewGamePageFragment();
        fragment1.getGame(game);
        fragment2 = new GameReportFragment();
        adapter.addFragment(fragment1, "Fragment 1");
        adapter.addFragment(fragment2, "fragment 2");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }

    public Game getGame() {
        return this.game;
    }

    public void updateGameReport(Game game) {
        fragment2.update(game);
    }


}
