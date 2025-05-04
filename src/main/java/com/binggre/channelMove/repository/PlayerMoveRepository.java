package com.binggre.channelMove.repository;

import com.binggre.binggreapi.utils.file.FileManager;
import com.binggre.channelMove.objects.PlayerMove;
import com.binggre.mongolibraryplugin.base.MongoRedisRepository;
import com.binggre.velocitysocketclient.VelocityClient;
import org.bson.Document;
import org.bukkit.plugin.Plugin;
import redis.clients.jedis.Jedis;

import java.util.UUID;

public class PlayerMoveRepository extends MongoRedisRepository<UUID, PlayerMove> {

    public PlayerMoveRepository(Plugin plugin, String database, String collection, String key, Class<PlayerMove> managementClass) {
        super(plugin, database, collection, key, managementClass);
    }

    @Override
    protected Jedis resource() {
        return VelocityClient.getInstance().getResource();
    }

    @Override
    public Document toDocument(PlayerMove playerMove) {
        return Document.parse(FileManager.toJson(playerMove));
    }

    @Override
    public PlayerMove toEntity(Document document) {
        return FileManager.toObject(document.toJson(), PlayerMove.class);
    }

    @Override
    public void putIn(PlayerMove entity) {
        super.remove(entity.getId());
        super.putIn(entity);
    }
}
