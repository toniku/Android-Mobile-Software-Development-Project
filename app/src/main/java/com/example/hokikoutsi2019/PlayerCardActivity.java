/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hokikoutsi2019.Classes.Player;

import org.w3c.dom.Text;

public class PlayerCardActivity extends AppCompatActivity {

    private TextView playerNameTextView;
    private Player player;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playercard);
        Intent i = getIntent();
        player = (Player) i.getSerializableExtra("playerObject");
        Log.d("LOL", "CARD: " + player.getLastname());
        playerNameTextView = findViewById(R.id.playerNameTextView);

        final TextView phoneNumberTextView = findViewById(R.id.playerPhoneNumberTextView);
        final TextView addressTextView = findViewById(R.id.textView16);
        final TextView cityTextView = findViewById(R.id.textView18);

        try
        {
            String phoneNumber = player.getContact().getPhoneNumber();
            phoneNumberTextView.setText(phoneNumber);

            String address = player.getContact().getAddress();
            addressTextView.setText(address);

            String city = player.getContact().getZipCode() + " " + player.getContact().getCity();
            cityTextView.setText(city);
        }
        catch (Exception e)
        {
            Log.d("LOL", e.toString());
            phoneNumberTextView.setText("");
            addressTextView.setText("");
            cityTextView.setText("");
        }

        phoneNumberTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberTextView.getText().toString();
                Uri number = Uri.parse("tel:" + player.getContact().getPhoneNumber());
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });
    }
}
