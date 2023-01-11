package com.niit.C13_s5_mc.repositorytest;


import com.niit.C13_s5_mc.domain.Artist;
import com.niit.C13_s5_mc.domain.Track;
import com.niit.C13_s5_mc.repository.TrackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
    TrackRepository trackRepository;

    Track track;
    Artist artist;

    @BeforeEach
    public void setUp(){
        this.artist = new Artist(212,"Himesh");
        this.track = new Track(978,"Hosoor",5.2,artist);
    }

    @Test
    @DisplayName("This is the test for adding tracks")
    public void addTracks(){
        trackRepository.save(track);
        Track track1 =trackRepository.findById(track.getTrackId()).get();

        assertEquals(track1,track);
    }

    @Test
    @DisplayName("This is the test for getting all tracks")
    public void getAllTracks(){
        trackRepository.delete(track);
        trackRepository.save(track);
        List<Track> list = trackRepository.findAll();

        assertEquals(1,list.size());
    }

    @Test
    @DisplayName("This is the test for deleting track")
    public void getAllTrack(){
        trackRepository.deleteAll();
        this.artist = new Artist(213,"ritesh");
        this.track = new Track(958,"hona hona",4.2,artist);
        trackRepository.save(track);
        Track track1 = trackRepository.findById(track.getTrackId()).get();
        trackRepository.deleteById(track.getTrackId());

        assertEquals(Optional.empty(),trackRepository.findById(track1.getTrackId()));
    }

    @AfterEach
    public void tearDown(){
        this.artist = null;
        this.track = null;
    }
}
