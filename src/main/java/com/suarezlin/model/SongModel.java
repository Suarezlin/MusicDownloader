package com.suarezlin.model;


public class SongModel {

    private String songName;

    private String artist;

    private String album;

    public SongModel(String songName, String artist, String album) {
        this.songName = songName;
        this.artist = artist;
        this.album = album;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "SongModel{" +
                "songName='" + songName + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                '}';
    }
}
