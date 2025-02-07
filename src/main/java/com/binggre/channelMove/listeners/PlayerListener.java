package com.binggre.channelMove.listeners;

import com.binggre.binggreapi.utils.command.CommandManager;
import com.binggre.channelMove.ChannelMove;
import com.binggre.channelMove.listeners.velocity.WarpListener;
import com.binggre.channelMove.objects.MoveChannelObject;
import com.binggre.channelMove.repository.MoveChannelObjectRepository;
import com.binggre.velocitysocketclient.VelocityClient;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerListener implements Listener {

    private final MoveChannelObjectRepository repository = ChannelMove.getInstance().getRepository();

    @EventHandler
    public void onClickNPC(NPCRightClickEvent event) {
        int id = event.getNPC().getId();

        for (MoveChannelObject moveChannelObject : repository.getCache().values()) {
            if (id == moveChannelObject.getNpc()) {
                performCommand(event.getClicker(), moveChannelObject);
                break;
            }
        }
    }

    private void performCommand(Player player, MoveChannelObject moveChannelObject) {
        VelocityClient.getInstance()
                .getConnectClient()
                .send(WarpListener.class, player.getName(), moveChannelObject.getWarpCommand());
        CommandManager.runCommand(player, "채널 이동 " + moveChannelObject.getChannel(), true);
    }
}