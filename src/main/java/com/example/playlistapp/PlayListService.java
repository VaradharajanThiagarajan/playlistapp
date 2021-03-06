package com.example.playlistapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public PlayListEntity getPLByName(String name) {
       return playListRepository.findByName(name);
    }

    public Object update(String playListName, String songName) throws Exception {
        PlayListEntity playListEntity=playListRepository.findByName(playListName);
        if (playListEntity!=null){
            playListEntity.addSong(songName);
        }
        else {
            return new Exception("Playlist does not exist");
        }
        return playListRepository.save(playListEntity);
    }

    public Object delete(String playListName, String songName) {

        PlayListEntity playListEntity=playListRepository.findByName(playListName);
        if (playListEntity!=null){
            playListEntity.deleteSong(songName);
        }
        else {
            return new Exception("Playlist does not exist");
        }
        return playListRepository.save(playListEntity);
    }
}
