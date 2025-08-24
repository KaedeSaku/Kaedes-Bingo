package com.kaedesbingo;

import com.google.inject.Provides;
import java.time.Instant;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.StatChanged;
import net.runelite.api.ChatMessageType;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import com.kaedesbingo.model.BingoState;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
        name = "Kaede's Bingo",
        description = "Interactive clan bingo tracker",
        tags = {"bingo", "clan", "event"}
)
public class KaedesBingoPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private ClientToolbar clientToolbar;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private net.runelite.client.plugins.kaedesbingo.KaedesBingoOverlay overlay;

    @Inject
    private net.runelite.client.plugins.kaedesbingo.KaedesBingoPanel panel;

    @Inject
    private net.runelite.client.plugins.kaedesbingo.KaedesBingoConfig config;

    @Inject
    private net.runelite.client.plugins.kaedesbingo.TaskEngine taskEngine;

    @Inject
    private net.runelite.client.plugins.kaedesbingo.GroupService groupService;

    private NavigationButton navButton;
    private final BingoState state = new BingoState();

    @Override
    protected void startUp()
    {
        navButton = NavigationButton.builder()
                .tooltip("Kaede's Bingo")
                .panel(panel)
                .build();

        clientToolbar.addNavigation(navButton);

        overlayManager.add(overlay);
        overlay.bindState(state, config);

        log.info("Kaede's Bingo started");
    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(overlay);
        if (navButton != null)
        {
            clientToolbar.removeNavigation(navButton);
            navButton = null;
        }
        log.info("Kaede's Bingo stopped");
    }

    @Subscribe
    public void onChatMessage(ChatMessage e)
    {
        if (e.getType() != ChatMessageType.GAMEMESSAGE
                && e.getType() != ChatMessageType.SPAM
                && e.getType() != ChatMessageType.FRIENDSCHAT)
        {
            return;
        }

        final String rsn = client.getLocalPlayer() != null ? client.getLocalPlayer().getName() : "Me";

        taskEngine.handleChat(e.getMessage(), Instant.now(), state, (completion) ->
        {
            if (completion.getBy() == null)
            {
                completion.setBy(rsn);
            }
            state.applyCompletion(completion);
            panel.refreshBoard();
            groupService.enqueueSync(completion);
        });
    }

    @Subscribe
    public void onStatChanged(StatChanged e)
    {
        final String rsn = client.getLocalPlayer() != null ? client.getLocalPlayer().getName() : "Me";

        taskEngine.handleStatChange(e, Instant.now(), state, (completion) ->
        {
            if (completion.getBy() == null)
            {
                completion.setBy(rsn);
            }
            state.applyCompletion(completion);
            panel.refreshBoard();
            groupService.enqueueSync(completion);
        });
    }

    @Provides
    KaedesBingoConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(KaedesBingoConfig.class);
    }
}