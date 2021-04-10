package com.example.playlistapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayListRepository extends JpaRepository <PlayListEntity,Long > {

    List<Object> findByName(String playlistName);
}
