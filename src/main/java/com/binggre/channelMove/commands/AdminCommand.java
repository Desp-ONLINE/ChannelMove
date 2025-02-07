package com.binggre.channelMove.commands;

import com.binggre.binggreapi.command.BetterCommand;
import com.binggre.binggreapi.command.CommandArgument;
import com.binggre.channelMove.commands.arguments.MoveArgument;
import com.binggre.channelMove.commands.arguments.ReloadArgument;
import com.binggre.channelMove.commands.arguments.WarpArgument;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AdminCommand extends BetterCommand implements TabCompleter {

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
        return List.of(new MoveArgument(), new ReloadArgument(), new WarpArgument());
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
