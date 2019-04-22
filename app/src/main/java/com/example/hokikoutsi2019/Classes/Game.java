/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    private ArrayList gameEvents = new ArrayList();
    private int homeGoals;
    private int awayGoals;
    private String homeTeam;
    private String awayTeam;

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = 0;
        this.awayGoals = 0;
    }

    public ArrayList<GoalEvent> getGameEvents() {
        return gameEvents;
    }

    public String getHomeTeam() {
        return this.homeTeam;
    }

    public void setHomeTeam(String team) {
        this.homeTeam = team;
    }

    public String getAwayTeam() {
        return this.awayTeam;
    }

    public void setAwayTeam(String team) {
        this.awayTeam = team;
    }

    public int getHome_goals() {
        return this.homeGoals;
    }

    public int getAway_goals() {
        return this.awayGoals;
    }

    public void setHomeGoals(int goals) {
        this.homeGoals = goals;
    }

    public void setAwayGoals(int goals) {
        this.awayGoals = goals;
    }

    public void setHomeGoal(String scorer) {
        this.homeGoals++;
        GoalEvent goalEvent = new GoalEvent(scorer, homeGoals, awayGoals, true);
        gameEvents.add(goalEvent);
    }

    public void setAwayGoal(String scorer) {
        this.awayGoals++;
        GoalEvent goalEvent = new GoalEvent(scorer, homeGoals, awayGoals, false);
        gameEvents.add(goalEvent);
    }
}
