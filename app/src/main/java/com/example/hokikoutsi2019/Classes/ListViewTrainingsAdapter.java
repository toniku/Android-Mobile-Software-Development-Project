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
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hokikoutsi2019.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListViewTrainingsAdapter extends ArrayAdapter<Training> {

    public ListViewTrainingsAdapter(Context context, ArrayList<Training> trainings) {
        super(context,0, trainings);
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Training training = getItem(position);

        if (convertView == null) {
            int layoutId = 0;
            layoutId = R.layout.list_item_training;
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textViewTrainingName);
        textViewName.setText(training.getName());

        TextView textViewLevel = convertView.findViewById(R.id.textViewTrainingLevel);
        textViewLevel.setText(training.getLevel());
        Log.i("SAATANA", "VITTU");
        Log.i("SAATANA", "" + training.getLevel());

        return convertView;
    }

}