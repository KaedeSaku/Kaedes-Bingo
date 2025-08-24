package com.kaedesbingo;


import java.time.Instant;
import net.runelite.api.events.StatChanged;


interface TaskMatcher
{
    TaskCompletion matchChat(String msg, Instant when);
    TaskCompletion matchStat(StatChanged e, Instant when);
}