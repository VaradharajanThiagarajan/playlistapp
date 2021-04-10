package com.example.playlistapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PlayListServiceTests {
    @Mock
    PlayListRepository  playListRepository;

    @InjectMocks
    PlayListService subject;

    @Test
    void create(){
        PlayListDto playListDto = new PlayListDto("playlisttwo");
        subject.create(playListDto);
        verify(playListRepository).save(
                new PlayListEntity("playlisttwo")
        );
    }

}
