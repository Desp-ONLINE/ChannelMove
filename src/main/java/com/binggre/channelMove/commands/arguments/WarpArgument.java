package com.binggre.channelMove.commands.arguments;

import com.binggre.binggreapi.command.CommandArgument;
import com.binggre.binggreapi.utils.command.CommandManager;
import com.binggre.channelMove.ChannelMove;
import com.binggre.channelMove.listeners.velocity.WarpListener;
import com.binggre.channelMove.objects.MoveChannelObject;
import com.binggre.channelMove.repository.MoveChannelObjectRepository;
import com.binggre.velocitysocketclient.VelocityClient;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpArgument implements CommandArgument {

    private final MoveChannelObjectRepository repository = ChannelMove.getInstance().getRepository();

    // /채널 워프 <server> <command>
    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) commandSender;
        String serverName = args[1];
        CommandManager.runCommand(player, "채널 이동 " + serverName, true);
        VelocityClient.getInstance()
                .getConnectClient()
                .send(WarpListener.class, player.getName(), CommandManager.space(args, 2));
/*        for (MoveChannelObject moveChannelObject : repository.getCache().values()) {
            String warpCommand = moveChannelObject.getCommand();
            if (warpCommand == null) {
                continue;
            }
            if (warpCommand.startsWith("/")) {
                warpCommand = warpCommand.substring(1);
            }
            if (serverName.equalsIgnoreCase(args[1]) && warpCommand.equalsIgnoreCase(args[2])) {

                break;
            }
        }*/
        return true;
    }

    @Override
    public String getArg() {
        return "워프";
    }

    @Override
    public int length() {
        return 3;
    }

    @Override
    public String getDescription() {
        return "[이름]";
    }

    @Override
    public String getPermission() {
        return "channelmove.warp";
    }

    @Override
    public String getPermissionMessage() {
        return "§c권한이 없습니다.";
    }

    @Override
    public boolean onlyPlayer() {
        return true;
    }
}
