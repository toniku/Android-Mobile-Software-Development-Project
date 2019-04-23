/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hokikoutsi2019.NewGameActivity;
import com.example.hokikoutsi2019.R;


// In this case, the fragment displays simple text based on the page
public class NewGamePageFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    NewGameActivity newGameActivity;
    View view;
    private Game game;
    private TextView textViewScore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.game_fragment, container, false);

        TextView textViewAwayTeam = view.findViewById(R.id.textViewAwayTeam);
        textViewAwayTeam.setText(game.getAwayTeam());

        TextView textViewHomeTeam = view.findViewById(R.id.textViewHomeTeam);
        textViewHomeTeam.setText(game.getHomeTeam());

        textViewScore = view.findViewById(R.id.textViewScore);
        String score = game.getHome_goals() + "-" + game.getAway_goals();
        textViewScore.setText(score);

        Button buttonHomeGoal = view.findViewById(R.id.buttonHomeGoal);
        buttonHomeGoal.setOnClickListener(this);

        Button buttonHomePenalty = view.findViewById(R.id.buttonHomePenalty);
        buttonHomePenalty.setOnClickListener(this);

        Button buttonHomeSave = view.findViewById(R.id.buttonHomeSave);
        buttonHomeSave.setOnClickListener(this);

        Button buttonAwayGoal = view.findViewById(R.id.buttonAwayGoal);
        buttonAwayGoal.setOnClickListener(this);

        return view;
    }

    public void getGame(Game game) {
        this.game = game;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onClick(View view) {
        if (view == this.getView().findViewById(R.id.buttonHomeGoal)) {

            final Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.goal_dialog);
            final Button dialogButtonOk = dialog.findViewById(R.id.buttonOk);
            final Button dialogButtonCancel = dialog.findViewById(R.id.buttonCancel);
            final EditText editTextGoalScorer = dialog.findViewById(R.id.editTextScorer);
            final EditText editTextFirstAssist = dialog.findViewById(R.id.editTextFirstAssist);
            final EditText editTextSecondAssist = dialog.findViewById(R.id.editTextSecondAssist);
            final CheckBox checkBox = dialog.findViewById(R.id.checkBox);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        Log.d("LOL", "CheckBox: " + checkBox.isChecked());
                        editTextFirstAssist.setText("");
                        editTextFirstAssist.setEnabled(false);
                        editTextSecondAssist.setText("");
                        editTextSecondAssist.setEnabled(false);
                    } else if (!checkBox.isChecked()) {
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

                    String toastText;
                    if (firstAssist.length() > 0) {
                        toastText = "MAALI: " + scorer + " (" + firstAssist + ")";
                    } else {
                        toastText = "MAALI: " + scorer;
                    }
                    Toast toast = Toast.makeText(getContext(), toastText, Toast.LENGTH_SHORT);
                    toast.show();

                    newGameActivity = (NewGameActivity) getActivity();
                    newGameActivity.updateGameReport(game);
                }
            });
            dialog.show();
        } else if (view == this.getView().findViewById(R.id.buttonHomePenalty)) {

        } else if (view == this.getView().findViewById(R.id.buttonHomeSave)) {

        } else if (view == this.getView().findViewById(R.id.buttonAwayGoal)) {
            this.game.setAwayGoal("Peltola");
            String score = game.getHome_goals() + "-" + this.game.getAway_goals();
            textViewScore.setText(score);
            newGameActivity = (NewGameActivity) getActivity();
            newGameActivity.updateGameReport(this.game);
        }
    }

}

