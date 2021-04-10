package com.example.playlistapp;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode
@Getter
public class PlayListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    ArrayList<String> songs= new ArrayList<>();

    public PlayListEntity(String name) {
        this.name = name;
    }

    public PlayListEntity() {
    }

    public ArrayList<String> getSongs() {
        return songs;
    }
}
