package com.niit.C13_s5_mc.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Track {

    @Id
    private int trackId;
    private String trackName;
    private double trackRating;
    private Artist trackArtist;

    public Track() {
    }

    public Track(int trackId, String trackName, double trackRating, Artist trackArtist) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.trackRating = trackRating;
        this.trackArtist = trackArtist;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public double getTrackRating() {
        return trackRating;
    }

    public void setTrackRating(double trackRating) {
        this.trackRating = trackRating;
    }

    public Artist getTrackArtist() {
        return trackArtist;
    }

    public void setTrackArtist(Artist trackArtist) {
        this.trackArtist = trackArtist;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                ", trackRating=" + trackRating +
                ", trackArtist=" + trackArtist +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return trackId == track.trackId && Double.compare(track.trackRating, trackRating) == 0 && Objects.equals(trackName, track.trackName) && Objects.equals(trackArtist, track.trackArtist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackId, trackName, trackRating, trackArtist);
    }
}