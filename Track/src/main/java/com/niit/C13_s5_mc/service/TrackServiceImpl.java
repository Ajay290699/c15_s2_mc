package com.niit.C13_s5_mc.service;


import com.niit.C13_s5_mc.domain.Track;
import com.niit.C13_s5_mc.domain.User;
import com.niit.C13_s5_mc.exception.TrackAlreadyExistException;
import com.niit.C13_s5_mc.exception.TrackNotFoundException;
import com.niit.C13_s5_mc.proxy.TrackProxy;
import com.niit.C13_s5_mc.repository.IUserRepository;
import com.niit.C13_s5_mc.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements ITrackService {


    TrackProxy proxy;
    TrackRepository trackRepository;
    IUserRepository userRepository;

    @Autowired
    public TrackServiceImpl(TrackProxy proxy, TrackRepository trackRepository, IUserRepository userRepository) {
        this.proxy = proxy;
        this.trackRepository = trackRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        if (userRepository.findById(user.getEmail()).isEmpty()) {
            proxy.addUser(user);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public Track addTrack(Track track) throws TrackAlreadyExistException {
        if (trackRepository.findById(track.getTrackId()).isEmpty()) {
            return trackRepository.save(track);
        }
        throw new TrackAlreadyExistException();
    }

    @Override
    public List<Track> getAllTrack() {
        return trackRepository.findAll();
    }

    @Override
    public String deleteTrack(int id) throws TrackNotFoundException {
        if (trackRepository.findById(id).isEmpty()){
            throw new TrackNotFoundException();
        }
        return "Track Deleted Successfully";
    }

    @Override
    public List<Track> getAllTrackByRating(double rating) {
        return trackRepository.getAllTrackByRating(rating);
    }

    @Override
    public List<Track> getAllTrackByArtistName(String artistName) {
        return trackRepository.getAllTrackByArtistName(artistName);
    }
}

