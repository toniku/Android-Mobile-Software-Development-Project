package com.example.hokikoutsi2019.Classes;

import java.io.Serializable;

public class Team implements Serializable {
    private String teamName;

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public String getTeamName()
    {
        return this.teamName;
    }
}
