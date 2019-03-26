package com.example.hokikoutsi2019.Classes;

import java.io.Serializable;

public class SubTraining implements Serializable {
    private String name;
    private String info;
    private int repetitions;

    public  SubTraining()
    {

    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setRepetitions(int repetitions)
    {
        this.repetitions = repetitions;
    }

    public int getRepetitions()
    {
        return this.repetitions;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getInfo(){return this.info;}
}
