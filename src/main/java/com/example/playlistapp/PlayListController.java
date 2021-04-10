package com.example.playlistapp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayListController {
    PlayListService playListService;

    public PlayListController(PlayListService playListService){
        this.playListService = playListService;
    }

    @PostMapping("playlist")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPlayList(@RequestBody PlayListDto playListDto){
        this.playListService.create(playListDto);
    }


    @GetMapping("/playlist/{name}")
    public List<Object> getPlaylist(@PathVariable String name){
        return this.playListService.getPlayListByName(name);
    }

//
//    @GetMapping("playlist")
//    public List<PlayListDto> getBooks(){
//        return this.playListService.getPlayList();
//    }

}
