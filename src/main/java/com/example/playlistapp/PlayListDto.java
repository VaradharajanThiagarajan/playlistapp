package com.example.playlistapp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PlayListDto {
    private String name;

    public PlayListDto(String name) {
        this.name = name;
    }

    public PlayListDto() {
    }

    public String getName() {
        return name;
    }
}
