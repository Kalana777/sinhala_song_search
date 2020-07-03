package com.kalana.elastic.sinhalasongsearch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "test_index_my", type = "song", shards = 2)
public class Song {
    @Id
    private String track_id;
    private String english_track_name;
    private String sinhala_track_name;
    private String rating;
    private String english_album_name;
    private String sinhala_album_name;
    private String english_artist_name;
    private String sinhala_artist_name;
    private String artist_rating;
    private String lyrics;

    public Song() {
    }

    public Song(String track_id, String english_track_name, String sinhala_track_name, String rating, String english_album_name, String sinhala_album_name, String english_artist_name, String sinhala_artist_name, String artist_rating, String lyrics) {
        this.track_id = track_id;
        this.english_track_name = english_track_name;
        this.sinhala_track_name = sinhala_track_name;
        this.rating = rating;
        this.english_album_name = english_album_name;
        this.sinhala_album_name = sinhala_album_name;
        this.english_artist_name = english_artist_name;
        this.sinhala_artist_name = sinhala_artist_name;
        this.artist_rating = artist_rating;
        this.lyrics = lyrics;
    }

    public String getTrack_id() {
        return track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }

    public String getEnglish_track_name() {
        return english_track_name;
    }

    public void setEnglish_track_name(String english_track_name) {
        this.english_track_name = english_track_name;
    }

    public String getSinhala_track_name() {
        return sinhala_track_name;
    }

    public void setSinhala_track_name(String sinhala_track_name) {
        this.sinhala_track_name = sinhala_track_name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEnglish_album_name() {
        return english_album_name;
    }

    public void setEnglish_album_name(String english_album_name) {
        this.english_album_name = english_album_name;
    }

    public String getSinhala_album_name() {
        return sinhala_album_name;
    }

    public void setSinhala_album_name(String sinhala_album_name) {
        this.sinhala_album_name = sinhala_album_name;
    }

    public String getEnglish_artist_name() {
        return english_artist_name;
    }

    public void setEnglish_artist_name(String english_artist_name) {
        this.english_artist_name = english_artist_name;
    }

    public String getSinhala_artist_name() {
        return sinhala_artist_name;
    }

    public void setSinhala_artist_name(String sinhala_artist_name) {
        this.sinhala_artist_name = sinhala_artist_name;
    }

    public String getArtist_rating() {
        return artist_rating;
    }

    public void setArtist_rating(String artist_rating) {
        this.artist_rating = artist_rating;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
