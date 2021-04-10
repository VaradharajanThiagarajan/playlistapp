package com.example.playlistapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs // New Annotation
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PlayListAppIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void addPlayList() throws Exception {
        PlayListDto playListDto = new PlayListDto("playlistone");

        mockMvc.perform(post("/playlist")
                .content(objectMapper.writeValueAsString(playListDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
         .andDo(document("AddPlayList"));

//        mockMvc.perform(get("/playlist")
//        ).andExpect(status().isOk())
//                .andExpect(jsonPath("length()").value(1))
//                .andExpect(jsonPath("[0].name").value("playlistone"));

    }

    @Test
    public void getPlaylistByName() throws Exception {
        PlayListDto playListDto = new PlayListDto("playlistone");

        mockMvc.perform(post("/playlist")
                .content(objectMapper.writeValueAsString(playListDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

        mockMvc.perform(get("/playlist/playlistone")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(0))
                .andDo(document("playlistone"));
    }

//    When a playlist is created with existing name
//    Then a message is returned that it was unsuccessful
    @Test
    public void duplicatePlaylistTest() throws Exception {
        PlayListDto playListDto = new PlayListDto("playlistone");

        mockMvc.perform(post("/playlist")
                .content(objectMapper.writeValueAsString(playListDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

        mockMvc.perform(post("/playlist")
                .content(objectMapper.writeValueAsString(playListDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("message").value("Playlist already exists"));
    }

//    When a playlist is created without a name
//    Then a message is returned that a name is required.
    @Test
    public void blankPlaylistNameTest() throws Exception {
        PlayListDto playListDto = new PlayListDto("");

        mockMvc.perform(post("/playlist")
                .content(objectMapper.writeValueAsString(playListDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("message").value("Playlist name cannot be blank"));
    }
//
//
//    Given a playlist
//    When a song is added
//    Then the playlist have one more song
    @Test
    public void addSongToPlayList() throws Exception {
        PlayListDto playListDto = new PlayListDto("playlistone");

        mockMvc.perform(post("/playlist")
                .content(objectMapper.writeValueAsString(playListDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

        //Add a song
        mockMvc.perform(post("/playlist/playlistone")
                .content("Macarena")
                .contentType(MediaType.TEXT_PLAIN)
        ).andExpect(status().isOk());

        mockMvc.perform(get("/playlist/playlistone")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1));

        //Add another song
        mockMvc.perform(post("/playlist/playlistone")
                .content("Macarena 2")
                .contentType(MediaType.TEXT_PLAIN)
        ).andExpect(status().isOk());

        mockMvc.perform(get("/playlist/playlistone")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(2));

    }

    //Tring to add a song to a playlist which does not exist
    @Test
    public void addSongToNonExistentPlayList() throws Exception {
        //Add a song
        mockMvc.perform(post("/playlist/playlistone")
                .content("Macarena")
                .contentType(MediaType.TEXT_PLAIN)
        ).andExpect(jsonPath("message").value("Playlist does not exist"));
    }

//    Given a playlist has songs
//    When retrieve the playlist
//    Then see the songs on the playlist
    @Test
    public void getSongsFromPlayList() throws Exception {
        PlayListDto playListDto = new PlayListDto("playlistone");

        mockMvc.perform(post("/playlist")
                .content(objectMapper.writeValueAsString(playListDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

        //Add a song
        mockMvc.perform(post("/playlist/playlistone")
                .content("Song 1")
                .contentType(MediaType.TEXT_PLAIN)
        ).andExpect(status().isOk());

        //Add a song
        mockMvc.perform(post("/playlist/playlistone")
                .content("Song 2")
                .contentType(MediaType.TEXT_PLAIN)
        ).andExpect(status().isOk());

        mockMvc.perform(get("/playlist/playlistone")
        ).andExpect(status().isOk())
        .andExpect(jsonPath("[0]").value("Song 1"))
        .andExpect(jsonPath("[1]").value("Song 2"));
    }



}
