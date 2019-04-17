/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hokikoutsi2019.MainActivity;
import com.example.hokikoutsi2019.NewGameActivity;
import com.example.hokikoutsi2019.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;


// In this case, the fragment displays simple text based on the page
public class NewGamePageFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private Game game;
    private TextView textViewHomeTeam;
    private TextView textViewAwayTeam;
    private TextView textViewScore;
    NewGameActivity newGameActivity;
    View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("LOL2", "OnCreateView()");

        this.view = inflater.inflate(R.layout.game_fragment, container, false);

        textViewAwayTeam = view.findViewById(R.id.textViewAwayTeam);
        textViewAwayTeam.setText(game.getAwayTeam());

        textViewHomeTeam = view.findViewById(R.id.textViewHomeTeam);
        textViewHomeTeam.setText(game.getHomeTeam());

        textViewScore = view.findViewById(R.id.textViewScore);
        String score = game.getHome_goals() + "-" + game.getAway_goals();
        textViewScore.setText(score);

        Button buttonHomeGoal = (Button) view.findViewById(R.id.buttonHomeGoal);
        buttonHomeGoal.setOnClickListener(this);

        Button buttonHomePenalty = (Button) view.findViewById(R.id.buttonHomePenalty);
        buttonHomePenalty.setOnClickListener(this);

        Button buttonHomeSave = (Button) view.findViewById(R.id.buttonHomeSave);
        buttonHomeSave.setOnClickListener(this);

        Button buttonAwayGoal = (Button) view.findViewById(R.id.buttonAwayGoal);
        buttonAwayGoal.setOnClickListener(this);

        return view;

    }


    public void getGame(Game game)
    {
        this.game = game;
        Log.d("LOL2", "getGame()");
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    @Override
    public void onClick(View view) {
        if (view == this.getView().findViewById(R.id.buttonHomeGoal))
        {

            final Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.goal_dialog);
            final Button dialogButtonOk = (Button) dialog.findViewById(R.id.buttonOk);
            final Button dialogButtonCancel = (Button) dialog.findViewById(R.id.buttonCancel);
            final EditText editTextGoalScorer = (EditText) dialog.findViewById(R.id.editTextScorer);
            final EditText editTextFirstAssist = (EditText) dialog.findViewById(R.id.editText1Assist);
            final EditText editTextSecondAssist = (EditText) dialog.findViewById(R.id.editText2Assist);
            final CheckBox checkBox = (CheckBox) dialog.findViewById(R.id.checkBox);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBox.isChecked())
                    {
                        Log.d("LOL", "CheckBox: " + checkBox.isChecked());
                        editTextFirstAssist.setText("");
                        editTextFirstAssist.setEnabled(false);
                        editTextSecondAssist.setText("");
                        editTextSecondAssist.setEnabled(false);
                    }
                    else if (!checkBox.isChecked())
                    {
                        Log.d("LOL", "CheckBox: " + checkBox.isChecked());
                        editTextFirstAssist.setEnabled(true);
                        editTextSecondAssist.setEnabled(true);
                    }
                }
            });

            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("LOL", "Cancel clicked");
                    dialog.dismiss();
                }
            });

            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("LOL", "OK clicked");
                    String scorer = editTextGoalScorer.getText().toString();
                    String firstAssist = editTextFirstAssist.getText().toString();

                    game.setHomeGoal(scorer);
                    String score = game.getHome_goals() + "-" + game.getAway_goals();
                    textViewScore.setText(score);

                    dialog.dismiss();

                    String toastText = "";
                    if (firstAssist.length() > 0)
                    {
                        toastText = "MAALI: " + scorer + " (" + firstAssist + ")";
                    }
                    else
                    {
                        toastText = "MAALI: " + scorer;
                    }
                    Toast toast=Toast.makeText(getContext(),toastText,Toast.LENGTH_SHORT);
                    toast.show();

                    newGameActivity = (NewGameActivity) getActivity();
                    newGameActivity.updateGameReport(game);
                }
            });
            dialog.show();
        }
        else if (view == this.getView().findViewById(R.id.buttonHomePenalty))
        {
            Log.d("LOLOL", "HOME TEAM PENALTY");
        }
        else if (view == this.getView().findViewById(R.id.buttonHomeSave))
        {
            Log.d("LOLOL", "HOME TEAM SAVE");
        }
        else if (view == this.getView().findViewById(R.id.buttonAwayGoal))
        {
            game.setAwayGoal("Peltola");
            String score = game.getHome_goals() + "-" + game.getAway_goals();
            textViewScore.setText(score);
        }
    }

}

