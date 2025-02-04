package com.binggre.channelMove.objects;

import com.binggre.mongolibraryplugin.base.MongoData;
import lombok.Getter;

@Getter
public class MoveChannelObject implements MongoData<Integer> {

    private int id;
    private int npc;
    private String command;

    private String channel;
    private String warpCommand;

    @Override
    public Integer getId() {
        return id;
    }
}