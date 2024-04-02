package com.pli.codes.architectureacrobat.audio;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.MODULE)
public enum MusicTrack {
    BACKGROUND_MUSIC("audio/music.wav"),
    BACKGROUND_NOISE("audio/noise.mp3"),
    ;

    private final String path;

    MusicTrack(String path) {
        this.path = path;
    }
}
