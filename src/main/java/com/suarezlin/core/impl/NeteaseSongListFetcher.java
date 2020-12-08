package com.suarezlin.core.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.suarezlin.core.SongFetcher;
import com.suarezlin.core.SongListFetcher;
import com.suarezlin.model.SongListModel;
import com.suarezlin.model.SongModel;
import com.suarezlin.utils.HttpUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NeteaseSongListFetcher implements SongListFetcher {

    private final String BASE_URL = "https://music.163.com/api/playlist/detail?id=";


    @Override
    public SongListModel fetchSongList(String songListId) {
        ObjectNode res = HttpUtils.getJsonResponse(BASE_URL + songListId);

        int code = res.get("code").asInt();
        if (code != 200) {
            throw new RuntimeException("获取歌单 " + songListId + " 失败: " + res.get("message").asText());
        }

        String name = res.get("result").get("name").asText("");

        String creator = res.get("result").get("creator").get("nickname").asText("");

        SongListModel listModel = new SongListModel(songListId, name, creator, SongListModel.ListType.NETEASE, new ArrayList<>());

        JsonNode tracks = res.get("result").get("tracks");
        if (tracks.isArray()) {
            for (JsonNode track : tracks) {
                String songName = track.get("name").asText();

                List<String> artists = new ArrayList<>();
                JsonNode artistList = track.get("artists");
                if (artistList.isArray()) {
                    for (JsonNode artist : artistList) {
                        artists.add(artist.get("name").asText());
                    }
                }
                String artist = String.join(",", artists);
                String album = track.get("album").get("name").asText();

                SongModel song = new SongModel(songName, artist, album);
                listModel.getSongs().add(song);
            }
        }

        return listModel;
    }

    public static void main(String[] args) {
        NeteaseSongListFetcher fetcher = new NeteaseSongListFetcher();
        try {
            SongListModel songListModel = fetcher.fetchSongList("2187977543");
            System.out.println(songListModel);

            SongFetcher songFetcher = new NeteaseSongFetcher();
            songFetcher.getSongDownloadUrl(songListModel.getSongs().get(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
