package com.kaedesbingo;
import java.time.Instant;
import java.util.regex.Pattern;
import net.runelite.api.events.StatChanged;


class RegexChatMatcher implements TaskMatcher
{
    private final String taskId;
    private final Pattern p;


    RegexChatMatcher(String taskId, String regex)
    {
        this.taskId = taskId; this.p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }


    @Override public TaskCompletion matchChat(String msg, Instant when)
    {
        return p.matcher(msg).matches() ? new TaskCompletion(taskId, null, false) : null; // plugin sets RSN
    }


    @Override public TaskCompletion matchStat(StatChanged e, Instant when){ return null; }
}