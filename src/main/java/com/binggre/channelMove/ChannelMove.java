package com.binggre.channelMove;

import com.binggre.binggreapi.BinggrePlugin;
import com.binggre.channelMove.commands.AdminCommand;
import com.binggre.channelMove.config.ChannelConfig;
import com.binggre.channelMove.listeners.PlayerListener;
import com.binggre.channelMove.listeners.velocity.WarpListener;
import com.binggre.channelMove.repository.MoveChannelObjectRepository;
import com.binggre.mongolibraryplugin.base.MongoConfiguration;
import com.binggre.velocitysocketclient.VelocityClient;
import lombok.Getter;

import java.util.HashMap;

public final class ChannelMove extends BinggrePlugin {

    @Getter
    private static ChannelMove instance = null;
    public static final String DATA_BASE_NAME = "ChannelMove";

    @Getter
    private ChannelConfig channelConfig;
    @Getter
    private MoveChannelObjectRepository repository;

    @Override
    public void onEnable() {
        instance = this;
        channelConfig = new ChannelConfig(DATA_BASE_NAME, "Config");
        channelConfig.init();
        repository = new MoveChannelObjectRepository(this, DATA_BASE_NAME, "Objects", new HashMap<>());
        repository.init();
        saveResource("-npc.json", true);
        saveResource("-command.json", true);
        executeCommand(this, new AdminCommand());
        registerEvents(this, new PlayerListener());
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        VelocityClient.getInstance().getConnectClient().registerListener(WarpListener.class);
    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this, "BungeeCord");
        channelConfig.save();
    }
}
