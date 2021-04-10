package com.example.playlistapp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController

public class PlayListController {
    ArrayList<PlayListDto> playList;

    public void PlayListController(){
        playList = new ArrayList<PlayListDto>();
    }

    @PostMapping("playlist")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPlayList(@RequestBody PlayListDto playListDto){
      //  playList.add(playListDto);
    }


    @GetMapping("playlist")
 //   public String getPlayList(){return "[{\"name\":\"playlistone\"}]"; }
    public ArrayList<PlayListDto> getBooks(){
        return this.playList;
    }

}
