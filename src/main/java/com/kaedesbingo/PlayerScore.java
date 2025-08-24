package com.kaedesbingo;


import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;


public class PlayerScore
{
    @Getter
    private String name;
    @Setter
    @Getter
    private int score;
    private final String team;
    @Setter
    private Instant last;


    public PlayerScore(String name, int score, String team, Instant last)
    {
        this.name = name; this.score = score; this.team = team; this.last = last;
    }


    String displayName(){ return name; }
    String scoreString(){ return score + "/25"; }
    String team(){ return team; }
    String lastCompletedAgo(){
        if (last == null) return "-";
        long m = Math.max(0, Duration.between(last, Instant.now()).toMinutes());
        return m + "m ago";
    }


}