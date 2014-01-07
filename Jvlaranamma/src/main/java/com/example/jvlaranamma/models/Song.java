package com.example.jvlaranamma.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by johanr on 2014-01-03.
 */
public class Song implements Serializable, Comparable<Song> {


    private final int pos;
    private final String name;
    private final String album;
    private final String year;
    private final String mp3;
    private final String oga;
    private final String poster;

    public Song(int pos, int id, String name, String album, String year, String mp3, String oga, String poster) {
        this.pos = pos;
        this.name = name;
        this.album = album;
        this.year = year;
        this.mp3 = mp3;
        this.oga = oga;
        this.poster = poster;


    }

    @Override
    public int compareTo(Song song) {
        return 0;
    }
}
