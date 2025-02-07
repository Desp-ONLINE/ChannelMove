package com.binggre.channelMove.config;

import com.binggre.binggreapi.utils.file.FileManager;
import com.binggre.channelMove.ChannelMove;
import com.binggre.mongolibraryplugin.base.MongoConfiguration;
import lombok.Getter;
import org.bson.Document;

@Getter
public class ChannelConfig extends MongoConfiguration {

    public ChannelConfig(String database, String collection) {
        super(database, collection);
    }

    private long delay = 30;

    @Override
    public void init() {
        String json = super.getConfigDocument().toJson();
        ChannelConfig newInstance = FileManager.toObject(json, ChannelConfig.class);

        delay = newInstance.delay;
    }
}