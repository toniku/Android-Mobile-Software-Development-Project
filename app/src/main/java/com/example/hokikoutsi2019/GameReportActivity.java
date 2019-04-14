/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hokikoutsi2019.Classes.Game;
import com.example.hokikoutsi2019.Classes.GameEvent;
import com.example.hokikoutsi2019.Classes.GameEventAdapter;

import java.util.ArrayList;

public class GameReportActivity extends AppCompatActivity {
    private TextView textViewHomeTeam = null;
    private TextView textViewAwayTeam = null;
    private TextView textViewScore = null;
    ListView listView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_report);

        textViewHomeTeam = findViewById(R.id.textViewHomeTeam);
        textViewAwayTeam = findViewById(R.id.textViewAwayTeam);
        textViewScore = findViewById(R.id.textViewScore);
        listView =  findViewById(R.id.listView);

        Intent i = getIntent();
        Game game = (Game) i.getSerializableExtra("gameObject");
        Log.d("LOL", "Report: " + game.getAwayTeam() + " VS " + game.getHomeTeam());

        textViewHomeTeam.setText(game.getHomeTeam());
        textViewAwayTeam.setText(game.getAwayTeam());
        textViewScore.setText("" + game.getHome_goals() + "-" + game.getAway_goals());

        ArrayList<GameEvent> gameEvents = game.getGameEvents();
        GameEventAdapter gameEventAdapter = new GameEventAdapter(this, gameEvents);
        listView.setAdapter(gameEventAdapter);
    }
}
