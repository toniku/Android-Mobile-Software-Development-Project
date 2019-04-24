/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hokikoutsi2019.Classes.Game;
import com.example.hokikoutsi2019.Classes.GoalEvent;
import com.example.hokikoutsi2019.Classes.GoalEventAdapter;

import java.util.ArrayList;

public class GameReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_report);

        TextView textViewHomeTeam = findViewById(R.id.textViewHomeTeam);
        TextView textViewAwayTeam = findViewById(R.id.textViewAwayTeam);
        TextView textViewScore = findViewById(R.id.textViewScore);
        ListView listView = findViewById(R.id.listView);

        Intent i = getIntent();
        Game game = (Game) i.getSerializableExtra("gameObject");

        textViewHomeTeam.setText(game.getHomeTeam());
        textViewAwayTeam.setText(game.getAwayTeam());
        textViewScore.setText("" + game.getHome_goals() + "-" + game.getAway_goals());

        ArrayList<GoalEvent> goalList = game.getGameEvents();
        GoalEventAdapter goalEventAdapter = new GoalEventAdapter(this, goalList);
        listView.setAdapter(goalEventAdapter);
    }
}
