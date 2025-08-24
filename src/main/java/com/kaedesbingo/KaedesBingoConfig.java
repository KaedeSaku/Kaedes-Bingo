package com.kaedesbingo;


import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;


@ConfigGroup("kaedesbingo")
public interface KaedesBingoConfig extends Config
{
    @ConfigItem(
            keyName = "groupKey",
            name = "Group Keyphrase",
            description = "Keyphrase to join a bingo group (e.g., Zulrah-Sofa-42)")
    default String groupKey()
    {
        return "";
    }


    @ConfigItem(
            keyName = "groupPassword",
            name = "Group Password (optional)",
            description = "Password if the event is private")
    default String groupPassword()
    {
        return "";
    }


    @ConfigItem(
            keyName = "showOverlay",
            name = "Show Side Scoreboard",
            description = "Toggle the always-on scoreboard overlay")
    default boolean showOverlay(){ return true; }


    @ConfigItem(
            keyName = "overlayMaxEntries",
            name = "Overlay Max Entries",
            description = "How many players to show in compact overlay")
    default int overlayMaxEntries(){ return 12; }
}