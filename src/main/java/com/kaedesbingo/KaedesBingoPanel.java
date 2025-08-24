package com.kaedesbingo;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

import net.runelite.client.ui.PluginPanel;

public class KaedesBingoPanel extends PluginPanel
{

    @Inject
    public KaedesBingoPanel()
    {
        setLayout(new BorderLayout());

        // Title styling
        JLabel titleLabel = new JLabel("Kaede's Bingo");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(3, 1, 5, 5));
        JButton createButton = new JButton("Create Bingo");
        buttons.add(createButton);
        JButton joinButton = new JButton("Join Bingo");
        buttons.add(joinButton);
        JButton leaderboardButton = new JButton("Leaderboard");
        buttons.add(leaderboardButton);

        add(buttons, BorderLayout.CENTER);
    }

    public void refreshBoard() {
    }
}