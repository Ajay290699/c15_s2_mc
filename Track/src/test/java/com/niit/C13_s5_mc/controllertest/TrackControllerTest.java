package com.niit.C13_s5_mc.controllertest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.C13_s5_mc.controller.TrackController;
import com.niit.C13_s5_mc.domain.Artist;
import com.niit.C13_s5_mc.domain.Track;
import com.niit.C13_s5_mc.exception.TrackAlreadyExistException;
import com.niit.C13_s5_mc.exception.TrackNotFoundException;
import com.niit.C13_s5_mc.service.TrackServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TrackControllerTest {

    @Mock
    TrackServiceImpl trackService;

    @InjectMocks
    TrackController trackController;

    @Autowired
    MockMvc mockMvc;

    Track track;
    Artist artist;

    @BeforeEach
    public void setUp() {
        this.artist = new Artist(312, "Rakesh");
        this.track = new Track(548, "Hosoor", 5.2, artist);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
    }

    @Test
    public void givenTrackToSaveReturnTrackSuccess() throws Exception {
        when(trackService.addTrack(any())).thenReturn(track);
        mockMvc.perform(post("/api/v1/track").contentType(MediaType.APPLICATION_JSON).
                content(convertJsonToString(track))).andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenTrackToSaveReturnTrackFailure() throws Exception {
        when(trackService.addTrack(any())).thenThrow(TrackAlreadyExistException.class);
        mockMvc.perform(post("/api/v1/track").contentType(MediaType.APPLICATION_JSON).
                        content(convertJsonToString(track))).andExpect(status().isConflict()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenTrackToDeleteTrackSuccess() throws Exception {
        String result="Track Deleted Successfully";
        when(trackService.deleteTrack(anyInt())).thenReturn(result);
        mockMvc.perform(delete("/api/v1/track/548")
                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenTrackToDeleteTrackFailure() throws Exception {
        String result="Track Deleted Successfully";
        when(trackService.deleteTrack(anyInt())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(delete("/api/v1/track/548")
                        .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
    }

    private static String convertJsonToString(final Object ob){
        String result;
        ObjectMapper mapper= new ObjectMapper();
        try {
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            result = "Json parsen error";
        }
        return result;
    }

    @AfterEach
    public void tearDown() {
        this.artist = null;
        this.track = null;
    }
}
