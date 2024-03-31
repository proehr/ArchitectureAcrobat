package com.pli.codes.architectureacrobat.audio;

import lombok.Getter;

@Getter
public enum SoundTrack {
    WALK("audio/walking.mp3"),
    BUTTON_CLICK("audio/button.mp3"),
    SUCCESS("audio/success.mp3"),
    ;

    private final String path;

    SoundTrack(String path) {
        this.path = path;
    }
}
