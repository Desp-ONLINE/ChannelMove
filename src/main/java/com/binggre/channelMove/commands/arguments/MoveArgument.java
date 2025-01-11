package com.binggre.channelMove.commands.arguments;

import com.binggre.binggreapi.command.CommandArgument;
import com.binggre.channelMove.ChannelMove;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MoveArgument implements CommandArgument {

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) commandSender;
        String serverName = args[1];
        sendPlayerToServer(player, serverName);
        return true;
    }

    private void sendPlayerToServer(Player player, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        player.sendPluginMessage(ChannelMove.getInstance(), "BungeeCord", out.toByteArray());
    }

    @Override
    public String getArg() {
        return "이동";
    }

    @Override
    public int length() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "[이름]";
    }

    @Override
    public String getPermission() {
        return "channelmove.move";
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
