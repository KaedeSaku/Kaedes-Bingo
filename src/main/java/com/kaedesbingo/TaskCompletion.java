package com.kaedesbingo;


import lombok.Getter;

@Getter
public class TaskCompletion
{
    private final String taskId;
    private String by; // mutable so plugin can set RSN
    private final boolean team;
    TaskCompletion(String taskId, String by, boolean team){ this.taskId = taskId; this.by = by; this.team = team; }

    void setBy(String b){ this.by = b; }
}