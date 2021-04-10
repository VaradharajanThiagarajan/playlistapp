package com.example.playlistapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayListService {

    private final PlayListRepository playListRepository;

    @Autowired
    public PlayListService (PlayListRepository playListRepository){
        this.playListRepository=playListRepository;
    }
    public void create (PlayListDto playListDto){
        playListRepository.save(
                new PlayListEntity(playListDto.getName()));

    }


}
