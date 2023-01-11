package com.niit.C13_s5_mc.servicetest;


import com.niit.C13_s5_mc.domain.Artist;
import com.niit.C13_s5_mc.domain.Track;
import com.niit.C13_s5_mc.exception.TrackAlreadyExistException;
import com.niit.C13_s5_mc.exception.TrackNotFoundException;
import com.niit.C13_s5_mc.repository.TrackRepository;
import com.niit.C13_s5_mc.service.TrackServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrackServiceTest {

    @Mock
    TrackRepository trackRepository;

    @InjectMocks
    TrackServiceImpl trackService;

    Track track;
    Artist artist;

    @BeforeEach
    public void setUp() {
        this.artist = new Artist(312, "Rakesh");
        this.track = new Track(548, "Hosoor", 5.2, artist);
    }

    @Test
    public void givenTrackToSaveShouldReturnTrackSuccess() throws TrackAlreadyExistException {
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
        when(trackRepository.save(track)).thenReturn(track);
        assertEquals(track,trackService.addTrack(track));
        verify(trackRepository,times(1)).save(track);
    }

    @Test
    public void givenTrackToSaveShouldReturnTrackFailure() throws TrackAlreadyExistException{
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(track));
        assertThrows(TrackAlreadyExistException.class,()->trackService.addTrack(track));
    }

    @Test
    public void givenTrackToDeleteTrackSuccess() throws TrackNotFoundException {
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(track));
        String result =trackService.deleteTrack(track.getTrackId());
        assertEquals("Track Deleted Successfully",result);
    }

    @Test
    public void givenTrackToDeleteTrackFailure(){
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
        assertThrows(TrackNotFoundException.class,()->trackService.deleteTrack(track.getTrackId()));
    }

    @AfterEach
    public void tearDown() {
        this.artist = null;
        this.track = null;
    }
}
