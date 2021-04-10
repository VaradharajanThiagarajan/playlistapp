package com.example.playlistapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    public ArrayList<String> getPlayListByName(String playlistName) {
        PlayListEntity playListEntity= this.playListRepository.findAll().stream()
                .filter(p -> p.getName().equals(playlistName)
        ).collect(Collectors.toList()).stream().findFirst().orElse(null);

        return playListEntity.getSongs();
    }

    public PlayListDto findByName(PlayListDto playListDto) {
        PlayListEntity playListEntity = this.playListRepository.findByName(playListDto.getName());
        if (playListEntity != null)
            return new PlayListDto(playListEntity.getName());
        else
            return null;
    }
}
