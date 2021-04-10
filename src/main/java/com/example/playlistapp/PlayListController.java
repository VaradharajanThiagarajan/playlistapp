package com.example.playlistapp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PlayListController {
    PlayListService playListService;

    public PlayListController(PlayListService playListService){
        this.playListService = playListService;
    }

    @PostMapping("playlist")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addPlayList(@RequestBody PlayListDto playListDto){
        PlayListDto playListDto1 = this.playListService.findByName(playListDto);

        if (playListDto.getName().trim().equals("")){
            return new Exception("Playlist name cannot be blank");
        }
        else if (playListDto1 != null) {
            return new Exception("Playlist already exists");
        }
        else{
            this.playListService.create(playListDto);
            return null;
        }
    }


    @GetMapping("/playlist/{name}")
    public ArrayList<String> getPlaylist(@PathVariable String name){
        return this.playListService.getPlayListByName(name);
    }


    @PostMapping("/playlist/{name}")
    public Object postSongToPlayList(@PathVariable String name,@RequestBody String songName) throws Exception {



       return  playListService.update(name,songName);


    }

//
//    @GetMapping("playlist")
//    public List<PlayListDto> getBooks(){
//        return this.playListService.getPlayList();
//    }

}
