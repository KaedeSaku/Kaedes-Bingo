package com.kaedesbingo;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.inject.Inject;
import net.runelite.api.Skill;
import net.runelite.api.events.StatChanged;
import com.kaedesbingo.model.BingoState;


class TaskEngine
{
    private final List<TaskMatcher> matchers = new ArrayList<>();


    @Inject
    TaskEngine()
    {
// Register built-in example matchers — replace with your board wiring
        matchers.add(new RegexChatMatcher("zulrah-kc-1", ".*Your Zulrah kill count is: \\d+.*"));
        matchers.add(new RegexChatMatcher("clue-scroll-any", ".*You have been awarded a clue scroll!.*"));
        matchers.add(new RegexChatMatcher("collection-log", ".*New item added to your collection log: .*"));
        matchers.add(new LevelUpMatcher("level-attack-50", Skill.ATTACK, 50));
    }


    void handleChat(String msg, Instant when, BingoState state, Consumer<TaskCompletion> onHit)
    {
        for (TaskMatcher m : matchers)
        {
            TaskCompletion c = m.matchChat(msg, when);
            if (c != null){ onHit.accept(c); }
        }
    }


    void handleStatChange(StatChanged e, Instant when, BingoState state, Consumer<TaskCompletion> onHit)
    {
        for (TaskMatcher m : matchers)
        {
            TaskCompletion c = m.matchStat(e, when);
            if (c != null){ onHit.accept(c); }
        }
    }
}