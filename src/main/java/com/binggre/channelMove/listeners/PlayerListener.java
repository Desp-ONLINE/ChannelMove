package com.binggre.channelMove.listeners;

import com.binggre.binggreapi.utils.command.CommandManager;
import com.binggre.channelMove.ChannelMove;
import com.binggre.channelMove.objects.MoveChannelObject;
import com.binggre.channelMove.objects.PlayerMove;
import com.binggre.channelMove.repository.MoveChannelObjectRepository;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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
        PlayerMove playerMove = new PlayerMove(player.getUniqueId(), moveChannelObject.getWarpCommand());
        ChannelMove.getInstance().getMoveRepository().putIn(playerMove);

        CommandManager.runCommand(player, "채널 이동 " + moveChannelObject.getChannel(), true);
    }
}