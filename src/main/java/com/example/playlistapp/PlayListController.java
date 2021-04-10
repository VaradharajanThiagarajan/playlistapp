package com.example.playlistapp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PlayListController {

    @PostMapping("playlist")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPlayList(){

    }

    @GetMapping("playlist")
    public String getPlayList(){return "[{}]"; }
}
