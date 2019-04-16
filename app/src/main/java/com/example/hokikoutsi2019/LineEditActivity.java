/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.hokikoutsi2019.Classes.DefenceFragmentPagerAdapter;
import com.example.hokikoutsi2019.Classes.GoalieFragmentPagerAdapter;
import com.example.hokikoutsi2019.Classes.OffenceFragmentPagerAdapter;

public class LineEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button doesn't work!

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new OffenceFragmentPagerAdapter(getSupportFragmentManager(),
                LineEditActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager2 = findViewById(R.id.viewpager2);
        viewPager2.setAdapter(new DefenceFragmentPagerAdapter(getSupportFragmentManager(),
                LineEditActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout2 = findViewById(R.id.sliding_tabs2);
        tabLayout2.setupWithViewPager(viewPager2);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager3 = findViewById(R.id.viewpager3);
        viewPager3.setAdapter(new GoalieFragmentPagerAdapter(getSupportFragmentManager(),
                LineEditActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout3 = findViewById(R.id.sliding_tabs3);
        tabLayout3.setupWithViewPager(viewPager3);
    }

}

