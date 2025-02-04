package com.binggre.channelMove.config;

import com.binggre.binggreapi.utils.file.FileManager;
import com.binggre.channelMove.ChannelMove;
import com.binggre.channelMove.objects.MoveChannelObject;
import com.binggre.mongolibraryplugin.base.MongoConfiguration;
import lombok.Getter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChannelConfig extends MongoConfiguration {

    private static ChannelConfig instance = null;

    public ChannelConfig(String database, String collection) {
        super(database, collection);
    }

    public static ChannelConfig getInstance() {
        if (instance == null) {
            instance = new ChannelConfig(ChannelMove.DATA_BASE_NAME, "Config");
        }
        return instance;
    }

    private long delay;

    @Override
    public void init() {
        Document configDocument = getConfigDocument();

        if (configDocument != null) {
            instance = FileManager.toObject(configDocument.toJson(), ChannelConfig.class);
        } else {
            instance.delay = 20;
        }
    }
}