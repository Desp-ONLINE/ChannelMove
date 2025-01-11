package com.binggre.channelMove.config;

import com.binggre.binggreapi.utils.file.FileManager;
import com.binggre.channelMove.ChannelMove;
import com.binggre.channelMove.objects.MoveChannelObject;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChannelConfig {

    private static ChannelConfig instance = null;
    private static final String PATH = ChannelMove.getInstance().getDataFolder().getPath();

    public static ChannelConfig getInstance() {
        if (instance == null) {
            instance = new ChannelConfig();
        }
        return instance;
    }

    private final List<MoveChannelObject> channelList = new ArrayList<>();

    public List<MoveChannelObject> getChannelList() {
        return new ArrayList<>(channelList);
    }

    public void init() {
        channelList.clear();
        List<File> files = FileManager.readFiles(PATH);

        for (File file : files) {
            MoveChannelObject read = FileManager.read(MoveChannelObject.class, file);
            channelList.add(read);
        }
    }
}