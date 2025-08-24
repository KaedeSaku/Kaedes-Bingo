package com.kaedesbingo.model;

import lombok.Getter;
import com.kaedesbingo.PlayerScore;
import com.kaedesbingo.TaskCompletion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BingoState
{
    @Getter
    private final BingoBoard board = BingoBoard.defaultBoard();
    private final List<PlayerScore> scores = new ArrayList<>();

    public List<PlayerScore> getTopScores(int limit)
    {
        return scores.stream()
                .sorted(Comparator.comparingInt(PlayerScore::getScore).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public void applyCompletion(TaskCompletion c)
    {
        if (c == null) return;

        // Update tiles
        for (BingoTile t : board.getTiles())
        {
            // FIXED: compare task ID instead of calling getTask().getTask()
            if (t.getTask().getId().equals(c.getTaskId()) && t.getState() == TileState.INCOMPLETE)
            {
                t.complete(c.getBy(), c.isTeam());
            }
        }

        // Update local score list
        PlayerScore playerScore = null;
        for (PlayerScore s : scores)
        {
            if (s.getName().equals(c.getBy()))
            {
                playerScore = s;
                break;
            }
        }

        if (playerScore == null)
        {
            scores.add(new PlayerScore(c.getBy(), 1, "Me", Instant.now()));
        }
        else
        {
            playerScore.setScore(playerScore.getScore() + 1);
            playerScore.setLast(Instant.now());
        }
    }
}