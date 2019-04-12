/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

public class Game {
    private int homeGoals;
    private int awayGoals;

    public Game(int home_goals, int away_goals)
    {
        this.homeGoals = home_goals;
        this.awayGoals = away_goals;
    }

    public int getHome_goals(){return this.homeGoals;}
    public int getAway_goals(){return this.awayGoals;}

    public void setHomeGoals(int goals)
    {
        this.homeGoals = goals;
    }

    public void setAwayGoals(int goals)
    {
        this.awayGoals = goals;
    }
}
