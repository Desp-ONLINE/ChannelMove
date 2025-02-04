package com.binggre.channelMove.repository;

import com.binggre.binggreapi.utils.file.FileManager;
import com.binggre.channelMove.objects.MoveChannelObject;
import com.binggre.mongolibraryplugin.base.MongoCachedRepository;
import org.bson.Document;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class MoveChannelObjectRepository extends MongoCachedRepository<Integer, MoveChannelObject> {

    public MoveChannelObjectRepository(Plugin plugin, String database, String collection, Map<Integer, MoveChannelObject> cache) {
        super(plugin, database, collection, cache);
    }

    @Override
    public Document toDocument(MoveChannelObject moveChannelObject) {
        return Document.parse(FileManager.toJson(moveChannelObject));
    }

    @Override
    public MoveChannelObject toEntity(Document document) {
        return FileManager.toObject(document.toJson(), MoveChannelObject.class);
    }

    public void init() {
        cache.clear();
        findAll().forEach(moveChannelObject -> {
            cache.put(moveChannelObject.getId(), moveChannelObject);
        });
    }
}