package com.binggre.channelMove.objects;

import com.binggre.mongolibraryplugin.base.MongoData;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class PlayerMove implements MongoData<UUID> {

    private final UUID id;
    private String warpCommand;

    @Override
    public UUID getId() {
        return id;
    }
}
