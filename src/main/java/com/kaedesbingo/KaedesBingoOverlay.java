package com.kaedesbingo;


import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import javax.inject.Inject;

import com.kaedesbingo.model.BingoState;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;


public class KaedesBingoOverlay extends Overlay
{
    private final PanelComponent panel = new PanelComponent();
    private BingoState state;
    private KaedesBingoConfig config;


    @Inject
    public KaedesBingoOverlay()
    {
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }


    void bindState(BingoState state, KaedesBingoConfig config)
    {
        this.state = state; this.config = config;
    }


    @Override
    public Dimension render(Graphics2D g)
    {
        panel.getChildren().clear();
        if (config == null || state == null || !config.showOverlay())
        {
            return null;
        }


        panel.setPreferredLocation(new Point(10, 40));
        panel.getChildren().add(LineComponent.builder().left("Bingo Scores").build());
        List<PlayerScore> scores = state.getTopScores(config.overlayMaxEntries());
        for (PlayerScore s : scores)
        {
            panel.getChildren().add(LineComponent.builder().left(s.displayName()).right(s.scoreString()).build());
        }
        return panel.render(g);
    }
}