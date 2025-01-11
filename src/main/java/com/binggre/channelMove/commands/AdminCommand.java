package com.binggre.channelMove.commands;

import com.binggre.binggreapi.command.BetterCommand;
import com.binggre.binggreapi.command.CommandArgument;
import com.binggre.channelMove.commands.arguments.MoveArgument;
import com.binggre.channelMove.commands.arguments.ReloadArgument;

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
        return List.of(new MoveArgument(), new ReloadArgument());
    }
}
