package com.example.hokikoutsi2019.Classes;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hokikoutsi2019.R;

import java.util.ArrayList;

public class ListViewTeamsAdapter extends ArrayAdapter<Team> {

    public ListViewTeamsAdapter(Context context, ArrayList<Team> teams) {
        super(context,0, teams);
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Team team = getItem(position);

        if (convertView == null) {
            int layoutId = 0;
            layoutId = R.layout.list_item_team;
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textViewTeamName);
        textViewName.setText(team.getTeamName());

        return convertView;
    }

}