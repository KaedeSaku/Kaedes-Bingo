package com.kaedesbingo;

import lombok.Getter;

@Getter
public class Task
{
    private final String name;
    private final String id;

    public Task(String name, String id)
    {
        this.name = name;
        this.id = id;
    }

}
