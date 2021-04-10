package com.example.playlistapp;
import lombok.Data;

@Data
public class PlayListDto {
    private String name;

    public PlayListDto(String name) {
        this.name = name;
    }
}
