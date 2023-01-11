package com.niit.C13_s5_mc.service;

import com.niit.C13_s5_mc.domain.Track;
import com.niit.C13_s5_mc.exception.TrackAlreadyExistException;
import com.niit.C13_s5_mc.exception.TrackNotFoundException;

import java.util.List;

public interface ITrackService {

    public Track addTrack(Track track) throws TrackAlreadyExistException;
    public List<Track> getAllTrack();
    public String deleteTrack(int id) throws TrackNotFoundException;

    public List<Track> getAllTrackByRating(double rating);

    public List<Track> getAllTrackByArtistName(String artistName);
}