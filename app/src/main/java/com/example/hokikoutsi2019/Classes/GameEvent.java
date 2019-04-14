/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameEvent implements Serializable {

    private String playerName;
    private String eventType;
    private boolean home;

    public int getHomeGoals() {
        return homeGoals;
    }

    private int homeGoals;

    public int getAwayGoals() {
        return awayGoals;
    }

    private int awayGoals;

    public GameEvent(String type, String player, boolean home, int homeGoals, int awayGoals)
    {
        eventType = type;
        playerName = player;
        this.home = home;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    public String getEventType() {
        return eventType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean getHome()
    {
        return this.home;
    }
}
