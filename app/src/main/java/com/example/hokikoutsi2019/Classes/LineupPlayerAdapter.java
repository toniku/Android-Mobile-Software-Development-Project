/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hokikoutsi2019.R;

import java.util.ArrayList;
import java.util.List;

public class LineupPlayerAdapter extends ArrayAdapter {

    private List list = new ArrayList();

    public LineupPlayerAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(LineupPlayer object) {
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
        LineupPlayerHolder lineupPlayerHolder;

        if (list_item_view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            list_item_view = layoutInflater.inflate(R.layout.lineup_player_list_item, parent, false);
            lineupPlayerHolder = new LineupPlayerHolder();
            lineupPlayerHolder.PlayerNameTextView = (TextView) list_item_view.findViewById(R.id.LineUpPlayerNameTextView);
            lineupPlayerHolder.PlayerJerseyTextView = (TextView) list_item_view.findViewById(R.id.LineUpPlayerJerseyTextView);
            list_item_view.setTag(lineupPlayerHolder);
        } else {
            lineupPlayerHolder = (LineupPlayerHolder) list_item_view.getTag();
        }

        LineupPlayer lineupPlayer = (LineupPlayer) this.getItem(position);
        assert lineupPlayer != null;
        lineupPlayerHolder.PlayerNameTextView.setText(lineupPlayer.getFirstName() + " " + lineupPlayer.getLastName());
        String jerseyNumber = Integer.toString(lineupPlayer.getJerseyNumber());
        lineupPlayerHolder.PlayerJerseyTextView.setText(jerseyNumber);
        return list_item_view;
    }

    static class LineupPlayerHolder {
        TextView PlayerNameTextView;
        TextView PlayerJerseyTextView;
    }
}