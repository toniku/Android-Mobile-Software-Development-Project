/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    private int homeGoals;
    private int awayGoals;
    private String homeTeam;
    private String awayTeam;

    public ArrayList<GameEvent> getGameEvents() {
        return gameEvents;
    }

    private ArrayList<GameEvent> gameEvents = new ArrayList<>();

    public Game(String homeTeam, String awayTeam, int home_goals, int away_goals)
    {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = home_goals;
        this.awayGoals = away_goals;
    }

    public String getHomeTeam(){return this.homeTeam;}
    public String getAwayTeam() {return this.awayTeam;}
    public int getHome_goals(){return this.homeGoals;}
    public int getAway_goals(){return this.awayGoals;}


    public void setHomeTeam(String team){this.homeTeam = team;}
    public void setAwayTeam(String team){this.awayTeam = team;}
    public void setHomeGoals(int goals)
    {
        this.homeGoals = goals;
    }
    public void setAwayGoals(int goals)
    {
        this.awayGoals = goals;
    }

    public void setGameEvent(int type, String player, boolean home)
    {
        //Goal
        if (type == 0)
        {
            //Home team scores
            if(home)
            {
                homeGoals++;
                GameEvent gameEventHomeGoal = new GameEvent("MAALI", player, home, homeGoals, awayGoals);
                gameEvents.add(gameEventHomeGoal);
            }
            else
            {
                awayGoals++;
                GameEvent gameEventAwayGoal = new GameEvent("MAALI", player, home, homeGoals, awayGoals);
                gameEvents.add(gameEventAwayGoal);

            }

        }
    }
}
