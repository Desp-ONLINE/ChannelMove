package com.binggre.channelMove.listeners;

import com.binggre.binggreapi.utils.command.CommandManager;
import com.binggre.channelMove.ChannelMove;
import com.binggre.channelMove.objects.PlayerMove;
import com.binggre.channelMove.repository.PlayerMoveRepository;
import fr.phoenixdevt.profiles.event.ProfileSelectEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WarpListener implements Listener {

    private final PlayerMoveRepository repository = ChannelMove.getInstance().getMoveRepository();

    @EventHandler
    public void onProfileSelect(ProfileSelectEvent event) {
        Player player = event.getPlayer();

        PlayerMove remove = repository.remove(player.getUniqueId());
        if (remove == null) {
            return;
        }
        String warpCommand = remove.getWarpCommand();
        if (warpCommand.startsWith("/")) {
            warpCommand = warpCommand.substring(1);
        }
        CommandManager.runCommand(event.getPlayer(), warpCommand, true);
    }
}