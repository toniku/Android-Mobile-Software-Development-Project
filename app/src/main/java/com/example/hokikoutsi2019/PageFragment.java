package com.example.hokikoutsi2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hokikoutsi2019.Classes.ListViewTrainingsAdapter;
import com.example.hokikoutsi2019.Classes.SubTraining;
import com.example.hokikoutsi2019.Classes.Training;

import java.util.ArrayList;


// In this case, the fragment displays simple text based on the page
public class PageFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String ARG_PAGE = "ARG_PAGE";
    ArrayList<Training> trainingArrayList = new ArrayList<>();
    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mPage == 1) {
            View view = inflater.inflate(R.layout.fragment_skating, container, false);

            trainingArrayList.clear();
            Training training = new Training();
            training.setName("Luisteluharjoitus1");
            training.setLevel(1);

            SubTraining subTraining = new SubTraining();
            subTraining.setName("Takaperinluistelu");
            subTraining.setRepetitions(5);
            subTraining.setInfo("Luistele takaperin ja kaarra takaisin");

            SubTraining subTraining2 = new SubTraining();
            subTraining2.setName("Suoraanluistelu");
            subTraining2.setRepetitions(4);
            subTraining2.setInfo("Luistele suoraan ja kaarra takaisin");

            training.setSubTraining(subTraining);
            training.setSubTraining(subTraining2);
            training.setSubTraining(subTraining);

            Training training2 = new Training();
            training2.setName("Luisteluharjoitus2");
            training2.setLevel(2);

            Training training3 = new Training();
            training3.setName("Luisteluharjoitus3");
            training3.setLevel(3);

            trainingArrayList.add(training);
            trainingArrayList.add(training2);
            trainingArrayList.add(training3);

            ListView lv = (ListView) view.findViewById(R.id.trainingListView);
            lv.setOnItemClickListener(this);
            lv.setAdapter(new ListViewTrainingsAdapter(getActivity(), trainingArrayList));

            return view;

        } else {
            View view = inflater.inflate(R.layout.fragment_page, container, false);
            TextView textView = (TextView) view;
            textView.setText("Fragment #" + mPage);
            return view;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try {

            Log.i("LOL", "Itemiä klikattu");
            Log.i("LOL", " " + i);

            Training training = trainingArrayList.get(i);
            Log.i("LOL", training.getName());

            Intent intent = new Intent(getActivity(), ShowTrainingListActivity.class);
            intent.putExtra("Training", training);
            startActivity(intent);
        } catch (Exception e) {
            Log.i("LOL", e.getMessage().toString());
        }
    }
}

