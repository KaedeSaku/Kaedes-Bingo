package com.kaedesbingo;


import com.kaedesbingo.model.BingoTile;
import com.kaedesbingo.model.TileState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


class TileView extends JPanel
{
    TileView(BingoTile tile)
    {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setPreferredSize(new Dimension(64,64));
        setBackground(colorFor(tile.getState()));


        JLabel title = new JLabel(shorten(tile.getTask().getName(), 28));
        title.setFont(getFont().deriveFont(Font.BOLD, 11f));
        JLabel who = new JLabel(tile.getCompletedBy() == null ? "" : ("by " + tile.getCompletedBy()));
        who.setFont(getFont().deriveFont(10f));


        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0; gc.gridy = 0; add(title, gc);
        gc.gridy = 1; add(who, gc);
    }


    private static String shorten(String s, int n){ return s.length() <= n ? s : s.substring(0, n-1) + "…"; }


    private static Color colorFor(TileState st)
    {
        switch (st)
        {
            case INCOMPLETE: return new Color(40, 40, 40);
            case COMPLETE_SELF: return new Color(20, 110, 40);
            case COMPLETE_TEAM: return new Color(25, 70, 120);
            case COMPLETE_OTHER: return new Color(120, 70, 25);
            default: return Color.GRAY;
        }
    }
}