package com.suarezlin.core.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suarezlin.core.SongFetcher;
import com.suarezlin.model.SongModel;
import com.suarezlin.utils.HttpUtils;

public class NeteaseSongFetcher implements SongFetcher {

    private static final String SEARCH_URL = "http://music.163.com/api/search/get/web?csrf_token=hlpretag=&hlposttag=&s={}&type=1&offset=0&total=true&limit=20";

    @Override
    public String getSongDownloadUrl(SongModel songModel) {
        searchSong(songModel);
        return null;
    }

    private void searchSong(SongModel songModel) {
        String search = songModel.getSongName() + " " + songModel.getArtist();
        JsonNode root = HttpUtils.getJsonResponse(SEARCH_URL.replace("{}", search));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(root));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
