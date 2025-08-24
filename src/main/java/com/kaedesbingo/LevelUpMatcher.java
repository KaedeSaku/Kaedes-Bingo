package com.kaedesbingo;


import java.time.Instant;
import net.runelite.api.Skill;
import net.runelite.api.events.StatChanged;


class LevelUpMatcher implements TaskMatcher
{
    private final String taskId; private final Skill skill; private final int target;
    LevelUpMatcher(String taskId, Skill skill, int target){ this.taskId = taskId; this.skill = skill; this.target = target; }
    @Override public TaskCompletion matchChat(String msg, Instant when){ return null; }
    @Override public TaskCompletion matchStat(StatChanged e, Instant when)
    {
        if (e.getSkill() == skill && e.getLevel() >= target)
        {
            return new TaskCompletion(taskId, null, false);
        }
        return null;
    }
}