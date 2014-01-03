package com.example.jvlaranamma.async;

import android.os.AsyncTask;

import com.example.jvlaranamma.models.Song;
import com.example.jvlaranamma.service.ArchiveSongs;

/**
 * Created by johanr on 2014-01-03.
 */
public class SongsLoaderAsyncTask extends AsyncTask<Void,Void,Song[]> {

    private boolean fullRefresh;

    public SongsLoaderAsyncTask(boolean fullRefresh) {
        this.fullRefresh = fullRefresh;
    }
    //private EpisodeListFragment archiveFragment;

    public interface UpdateCallback {
        public void onUpdate();
    }

    public static UpdateCallback getVoidCallback() {
        return new UpdateCallback() {

            @Override
            public void onUpdate() {

            }
        };
    }

    @Override
    protected Song[] doInBackground(Void... voids) {
        Song[] songs = ArchiveSongs.getInstance().getSongs(new UpdateCallback() {

            @Override
            public void onUpdate() {
                publishProgress();
            }
        }, fullRefresh);

        return songs;
    }
}
