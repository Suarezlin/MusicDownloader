package com.suarezlin.core;

import com.suarezlin.model.SongModel;

public interface SongFetcher {

    String getSongDownloadUrl(SongModel songModel);

}
