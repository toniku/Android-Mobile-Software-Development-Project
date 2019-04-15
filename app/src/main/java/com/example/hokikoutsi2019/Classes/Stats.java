/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

import java.io.Serializable;

public class Stats implements Serializable {

    private int goals;
    private int penaltyShotGoals;

    private int firstAssists;
    private int secondaryAssists;

    private int seasonStartYear;

    public Stats(int seasonStartYear) {
        this.goals = 0;
        this.penaltyShotGoals = 0;

        this.firstAssists = 0;
        this.secondaryAssists = 0;

        this.seasonStartYear = seasonStartYear;
    }

    public void setGoal() {
        this.goals++;
    }

    public int getGoals()
    {
        return this.goals;
    }
}
