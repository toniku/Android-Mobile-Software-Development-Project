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
import com.google.android.gms.flags.IFlagProvider;

import java.util.ArrayList;

public class GameEventAdapter extends ArrayAdapter<GameEvent> {

    public GameEventAdapter(Context context, ArrayList<GameEvent> gameEvents) {
        super(context,0, gameEvents);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GameEvent gameEvent = getItem(position);

        if (convertView == null) {
            int layoutId = 0;
            layoutId = R.layout.report_list_item;
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        if (gameEvent.getHome())
        {
            TextView textViewEvent = convertView.findViewById(R.id.textViewHomeEvent);
            textViewEvent.setText(gameEvent.getPlayerName() + System.getProperty ("line.separator") + gameEvent.getHomeGoals() + "-" + gameEvent.getAwayGoals());

            TextView textViewEventName = convertView.findViewById(R.id.textViewEventName);
            textViewEventName.setText(gameEvent.getEventType());
        }
        else
        {
            TextView textViewEvent = convertView.findViewById(R.id.textViewAwayEvent);
            textViewEvent.setText(gameEvent.getPlayerName() + System.getProperty ("line.separator") + gameEvent.getHomeGoals() + "-" + gameEvent.getAwayGoals());

            TextView textViewEventName = convertView.findViewById(R.id.textViewEventName);
            textViewEventName.setText(gameEvent.getEventType());
        }


        return convertView;
    }

}