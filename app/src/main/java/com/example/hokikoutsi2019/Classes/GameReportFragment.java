/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hokikoutsi2019.NewGameActivity;
import com.example.hokikoutsi2019.R;

import java.util.ArrayList;

public class GameReportFragment extends Fragment {

    private TextView textViewScore;
    private Game game;
    private ListView listView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_fragment, container, false);

        NewGameActivity newGameActivity = (NewGameActivity) getActivity();
        this.game = newGameActivity.getGame();

        TextView textViewHomeTeam = view.findViewById(R.id.textViewHomeTeam);
        textViewHomeTeam.setText(game.getHomeTeam());

        TextView textViewAwayTeam = view.findViewById(R.id.textViewAwayTeam);
        textViewAwayTeam.setText(game.getAwayTeam());

        String score = game.getHome_goals() + "-" + game.getAway_goals();
        textViewScore = view.findViewById(R.id.textViewScore);
        textViewScore.setText(score);

        listView = view.findViewById(R.id.listView);

        return view;
    }

    public void update(Game game) {
        this.game = game;
        String score = game.getHome_goals() + "-" + game.getAway_goals();
        textViewScore.setText(score);

        ArrayList<GoalEvent> goalList = game.getGameEvents();
        int i = 0;
        while (i < goalList.size()) {
            Log.d("GoalList", goalList.get(i).getScorer() + " " + goalList.get(i).getHomeGoals() + "-" + goalList.get(i).getAwayGoals());
            i++;

        }
        GoalEventAdapter goalEventAdapter = new GoalEventAdapter(getContext(), goalList);
        listView.setAdapter(goalEventAdapter);
    }

}
