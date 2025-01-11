package com.binggre.channelMove.listeners;

import com.binggre.channelMove.config.ChannelConfig;
import com.binggre.channelMove.listeners.velocity.WarpListener;
import com.binggre.channelMove.objects.MoveChannelObject;
import com.binggre.velocitysocketclient.VelocityClient;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String eventCommand = event.getMessage();
        if (eventCommand.startsWith("/")) {
            eventCommand = eventCommand.substring(1);
        }

        for (MoveChannelObject moveChannelObject : ChannelConfig.getInstance().getChannelList()) {
            String command = moveChannelObject.getCommand();
            if (command == null) {
                continue;
            }
            if (command.startsWith("/")) {
                command = command.substring(1);
            }
            if (command.equalsIgnoreCase(eventCommand)) {
                Player player = event.getPlayer();
                player.performCommand("채널 이동 " + moveChannelObject.getChannel());
                VelocityClient.getInstance()
                        .getConnectClient()
                        .send(WarpListener.class, player.getName(), moveChannelObject.getWarpCommand());
                break;
            }
        }
    }

    @EventHandler
    public void onClickNPC(NPCRightClickEvent event) {
        int id = event.getNPC().getId();

        for (MoveChannelObject moveChannelObject : ChannelConfig.getInstance().getChannelList()) {
            if (id == moveChannelObject.getNpc()) {
                Player player = event.getClicker();
                player.performCommand("채널 이동 " + moveChannelObject.getChannel());
                VelocityClient.getInstance()
                        .getConnectClient()
                        .send(WarpListener.class, player.getName(), moveChannelObject.getWarpCommand());
                break;
            }
        }
    }
}