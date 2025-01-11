package com.binggre.channelMove.listeners.velocity;

import com.binggre.channelMove.ChannelMove;
import com.binggre.velocitysocketclient.listener.VelocitySocketListener;
import com.binggre.velocitysocketclient.socket.SocketResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpListener extends VelocitySocketListener {

    @Override
    public void onReceive(String[] messages) {
        String playerName = messages[0];
        final String[] command = {messages[1]};

        Bukkit.getScheduler().runTaskLater(ChannelMove.getInstance(), () -> {
            Player player = Bukkit.getPlayer(playerName);
            if (player == null) {
                return;
            }
            String warpCommand = command[0];
            if (warpCommand.startsWith("/")) {
                warpCommand = warpCommand.substring(1);
            }
            player.performCommand(warpCommand);
        }, 30L);
    }

    @Override
    public @NotNull SocketResponse onRequest(String... strings) {
        return SocketResponse.empty();
    }

    @Override
    public void onResponse(SocketResponse socketResponse) {

    }
}
