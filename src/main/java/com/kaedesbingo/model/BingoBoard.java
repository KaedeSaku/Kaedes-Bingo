package com.kaedesbingo.model;

import lombok.Getter;
import com.kaedesbingo.Task;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BingoBoard
{
    private final List<BingoTile> tiles;

    public BingoBoard(List<BingoTile> tiles)
    {
        this.tiles = tiles;
    }

    /**
     * Creates a default 5x5 board with placeholder tasks for MVP.
     */
    public static BingoBoard defaultBoard()
    {
        List<BingoTile> tiles = new ArrayList<>();
        for (int i = 1; i <= 25; i++)
        {
            Task task = new Task("Task " + i, "task-" + i);
            tiles.add(new BingoTile(task));
        }
        return new BingoBoard(tiles);
    }
}
