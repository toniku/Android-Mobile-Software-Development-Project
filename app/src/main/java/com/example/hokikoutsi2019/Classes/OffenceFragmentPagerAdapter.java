/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class OffenceFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] Offence = new String[]{"Hyökkäys 1", "Hyökkäys 2"};

    public OffenceFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        Context context1 = context;
    }

    @Override
    public int getCount() {
        int PAGE_COUNT = 2;
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return OffencePageFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return Offence[position];
    }
}
