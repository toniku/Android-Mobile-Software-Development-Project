/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hokikoutsi2019.R;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends ArrayAdapter {

    private List list = new ArrayList();

    public GameAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Game object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View list_item_view;
        list_item_view = convertView;
        GameHolder gameHolder;

        if (list_item_view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            list_item_view = layoutInflater.inflate(R.layout.game_list_item, parent, false);
            gameHolder = new GameHolder();
            gameHolder.ScoreListView = (TextView) list_item_view.findViewById(R.id.textViewScore);
            gameHolder.HomeTeamName = (TextView) list_item_view.findViewById(R.id.textViewHomeTeam);
            gameHolder.AwayTeamName = (TextView) list_item_view.findViewById(R.id.textViewAwayTeam);
            list_item_view.setTag(gameHolder);
        } else {
            gameHolder = (GameHolder) list_item_view.getTag();
        }

        Game game = (Game) this.getItem(position);
        assert game != null;
        String homeGoals = Integer.toString(game.getHome_goals());
        String awayGoals = Integer.toString(game.getAway_goals());
        gameHolder.ScoreListView.setText(homeGoals + "-" + awayGoals);
        gameHolder.HomeTeamName.setText(game.getHomeTeam());
        gameHolder.AwayTeamName.setText(game.getAwayTeam());
        return list_item_view;
    }

    static class GameHolder {
        TextView ScoreListView;
        TextView HomeTeamName;
        TextView AwayTeamName;
    }
}