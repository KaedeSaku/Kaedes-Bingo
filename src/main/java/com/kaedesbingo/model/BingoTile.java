package com.kaedesbingo.model;

// Use your plugin's Task class, not RuneLite HTTP Task
import lombok.Getter;
import com.kaedesbingo.Task;

@Getter
public class BingoTile
{
    private final Task task;
    private TileState state;
    private String completedBy;

    public BingoTile(Task task)
    {
        this.task = task;
        this.state = TileState.INCOMPLETE;
        this.completedBy = null;
    }

    public void complete(String by, boolean team)
    {
        this.state = team ? TileState.COMPLETE_TEAM : TileState.COMPLETE_SELF;
        this.completedBy = by;
    }
}