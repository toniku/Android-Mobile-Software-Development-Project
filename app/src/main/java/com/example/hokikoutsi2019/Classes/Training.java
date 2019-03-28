/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Training implements Serializable {

    private String name;
    private String level;
    private ArrayList<SubTraining> subTrainingsArrayList = new ArrayList<>();

    public Training() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(int levelInt) {

        if (levelInt == 1) {
            this.level = "Aloittelija";
        } else if (levelInt == 2) {
            this.level = "Ammattilainen";
        } else if (levelInt == 3) {
            this.level = "Eliitti";
        }
    }

    public void setSubTraining(SubTraining subTraining) {
        subTrainingsArrayList.add(subTraining);
    }

    public ArrayList<SubTraining> getSubTrainingsArrayList() {
        return subTrainingsArrayList;
    }

    public int getSubTrainingsSize() {
        return subTrainingsArrayList.size();
    }
}
