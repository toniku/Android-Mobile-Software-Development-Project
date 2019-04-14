/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

import java.io.Serializable;

public class GoalEvent implements Serializable {

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public String getScorer() {
        return scorer;
    }

    public void setScorer(String scorer) {
        this.scorer = scorer;
    }

    private String scorer;

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    private int homeGoals;

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    private int awayGoals;

    public boolean isHomeScored() {
        return homeScored;
    }

    public void setHomeScored(boolean homeScored) {
        this.homeScored = homeScored;
    }

    private boolean homeScored;


    public GoalEvent(String scorer, int homeGoals, int awayGoals, boolean homeScored)
    {
        this.scorer = scorer;
        this.awayGoals = awayGoals;
        this.homeGoals = homeGoals;
        this.homeScored = homeScored;
        this.type = "MAALI";
    }
}
