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
