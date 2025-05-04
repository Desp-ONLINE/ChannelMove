package com.binggre.channelMove.commands;

import com.binggre.binggreapi.command.BetterCommand;
import com.binggre.binggreapi.command.CommandArgument;
import com.binggre.binggreapi.utils.command.CommandManager;
import com.binggre.channelMove.commands.arguments.MoveArgument;
import com.binggre.channelMove.commands.arguments.WarpArgument;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AdminCommand extends BetterCommand {

    @Override
    public String getCommand() {
        return "채널";
    }

    @Override
    public boolean isSingleCommand() {
        return false;
    }

    @Override
    public List<CommandArgument> getArguments() {
        return List.of(new MoveArgument(), new WarpArgument());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0 && sender instanceof Player player) {
            CommandManager.runCommand(player, "gui open 채널", true);
            return true;
        }
        return super.onCommand(sender, command, label, args);
    }
}