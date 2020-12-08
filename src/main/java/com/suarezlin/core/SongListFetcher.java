package com.suarezlin.core;

import com.suarezlin.model.SongListModel;
import com.suarezlin.model.SongModel;

import java.util.List;

public interface SongListFetcher {

    SongListModel fetchSongList(String songListId);

}
