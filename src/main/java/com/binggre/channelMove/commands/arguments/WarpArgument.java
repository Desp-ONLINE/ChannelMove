package com.binggre.channelMove.commands.arguments;

import com.binggre.binggreapi.command.CommandArgument;
import com.binggre.binggreapi.utils.command.CommandManager;
import com.binggre.channelMove.ChannelMove;
import com.binggre.channelMove.objects.PlayerMove;
import com.binggre.channelMove.repository.PlayerMoveRepository;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpArgument implements CommandArgument {

    private final PlayerMoveRepository repository = ChannelMove.getInstance().getMoveRepository();

    // /채널 워프 <server> <command>
    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) commandSender;
        String serverName = args[1];
        String warpCommand = CommandManager.space(args, 2);

        PlayerMove playerMove = new PlayerMove(player.getUniqueId(), warpCommand);
        repository.putIn(playerMove);

        CommandManager.runCommand(player, "채널 이동 " + serverName, true);
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
        return "[명령어]";
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
