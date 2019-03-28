/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hokikoutsi2019.Classes.SubTraining;

public class SubTrainingInfoDialog extends DialogFragment implements View.OnClickListener {

    private SubTraining subTraining;
    private TextView textViewName;


    public static SubTrainingInfoDialog newInstance(int arg, SubTraining subTraining) {
        SubTrainingInfoDialog frag = new SubTrainingInfoDialog();
        Bundle args = new Bundle();
        args.putInt("count", arg);
        frag.setArguments(args);
        frag.setSubTraining(subTraining);
        Log.i("LMAO", subTraining.getName());
        //Log.i("LMAO", subTraining.getInfo());
        return frag;
    }

    public void setSubTraining(SubTraining subTraining) {
        this.subTraining = subTraining;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.subtraininginfodialog, container, false);
        TextView textViewName = (TextView) v.findViewById(R.id.textViewInfoName);
        textViewName.setText(subTraining.getName());

        TextView textViewInfo = (TextView) v.findViewById(R.id.textViewInfo);
        textViewInfo.setText(subTraining.getInfo());

        ImageView youtubeLink = (ImageView) v.findViewById(R.id.imageViewYoutube);
        youtubeLink.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view == getView().findViewById(R.id.imageViewYoutube)) {
            Log.i("UUU", "Youtube clicked");
            watchYoutubeVideo(getActivity(), "ykwh7ZUXrJg");
        }

    }

    public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }
}
