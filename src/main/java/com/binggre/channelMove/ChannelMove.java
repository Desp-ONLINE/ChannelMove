package com.binggre.channelMove;

import com.binggre.binggreapi.BinggrePlugin;
import com.binggre.channelMove.commands.AdminCommand;
import com.binggre.channelMove.listeners.PlayerListener;
import com.binggre.channelMove.listeners.WarpListener;
import com.binggre.channelMove.objects.PlayerMove;
import com.binggre.channelMove.repository.MoveChannelObjectRepository;
import com.binggre.channelMove.repository.PlayerMoveRepository;
import lombok.Getter;

import java.util.HashMap;

@Getter
public final class ChannelMove extends BinggrePlugin {

    @Getter
    private static ChannelMove instance = null;
    public static final String DATA_BASE_NAME = "ChannelMove";

    private PlayerMoveRepository moveRepository;
    private MoveChannelObjectRepository repository;

    @Override
    public void onEnable() {
        instance = this;
        moveRepository = new PlayerMoveRepository(this, DATA_BASE_NAME, "Player", "PlayerChannelMove:", PlayerMove.class);

        repository = new MoveChannelObjectRepository(this, DATA_BASE_NAME, "Objects", new HashMap<>());
        repository.init();
        saveResource("-npc.json", true);
        saveResource("-command.json", true);
        executeCommand(this, new AdminCommand());
        registerEvents(this, new PlayerListener(), new WarpListener());
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this, "BungeeCord");
    }
}