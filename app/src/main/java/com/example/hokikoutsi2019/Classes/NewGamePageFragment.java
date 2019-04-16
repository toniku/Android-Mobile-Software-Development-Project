/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hokikoutsi2019.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


// In this case, the fragment displays simple text based on the page
public class NewGamePageFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    private static final String DESCRIBABLE_KEY = "describable_key";
    private Game game;

    public static NewGamePageFragment newInstance(int page, Game game) {

        NewGamePageFragment fragment = new NewGamePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE, page);
        bundle.putSerializable(DESCRIBABLE_KEY, game);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.game = (Game) getArguments().getSerializable(DESCRIBABLE_KEY);

        if (mPage == 1)
        {
            View view = inflater.inflate(R.layout.game_fragment, container, false);
            Log.d("LOLOL", game.getHomeTeam());

            TextView textViewHomeTeam =  view.findViewById(R.id.textViewHomeTeam);
            textViewHomeTeam.setText(game.getHomeTeam());

            TextView textViewAwayTeam =  view.findViewById(R.id.textViewAwayTeam);
            textViewAwayTeam.setText(game.getAwayTeam());

            String score = game.getHome_goals() + "-" + game.getAway_goals();
            TextView textViewScore = view.findViewById(R.id.textViewScore);
            textViewScore.setText(score);
            return view;

        }
        else if (mPage == 2)
        {
            View view = inflater.inflate(R.layout.lines_frament, container, false);
            return view;

        }
        else
        {
            View view = inflater.inflate(R.layout.event_fragment, container, false);
            return view;
        }


    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try {


        }
        catch (Exception e)
        {

        }
    }


}

