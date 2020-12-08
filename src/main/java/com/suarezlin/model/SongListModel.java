package com.suarezlin.model;

import java.util.List;

public class SongListModel {

    private String id;

    private String name;

    private String creator;

    private ListType type;

    private List<SongModel> songs;

    public static enum ListType {
        NETEASE("Netease Music");

        private String desc;

        ListType(String desc) {
            this.desc = desc;
        }
    }

    public SongListModel(String id, String name, String creator, ListType type, List<SongModel> songs) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.type = type;
        this.songs = songs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public ListType getType() {
        return type;
    }

    public void setType(ListType type) {
        this.type = type;
    }

    public List<SongModel> getSongs() {
        return songs;
    }

    public void setSongs(List<SongModel> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "SongListModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", type=" + type +
                ", songs=" + songs +
                '}';
    }
}
