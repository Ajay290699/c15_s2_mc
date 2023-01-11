package com.niit.C13_s5_mc.repository;

import com.niit.C13_s5_mc.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TrackRepository extends MongoRepository<Track, Integer> {

    @Query("{'trackRating':{$gt:?0}}")
    public List<Track> getAllTrackByRating(double rating);

    @Query("{'trackArtist.artistName':'?0'}")
    public List<Track> getAllTrackByArtistName(String artistName);
}