package com.pli.codes.architectureacrobat.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import java.util.EnumMap;

/**
 * Singleton audio controller class that manages the audio in the game.
 */
public class AudioController {

    private static AudioController instance;

    private final EnumMap<MusicTrack, Music> musicMap = new EnumMap<>(MusicTrack.class);
    private final EnumMap<SoundTrack, Sound> soundMap = new EnumMap<>(SoundTrack.class);

    private AudioController() {
    }

    public static AudioController getInstance() {
        if (instance == null) {
            instance = new AudioController();
        }
        return instance;
    }

    public void initialize() {
        for (MusicTrack value : MusicTrack.values()) {
            musicMap.put(value, Gdx.audio.newMusic(Gdx.files.internal(value.getPath())));
        }
        for (SoundTrack value : SoundTrack.values()) {
            soundMap.put(value, Gdx.audio.newSound(Gdx.files.internal(value.getPath())));
        }
    }

    public Music getMusic(MusicTrack musicTrack, boolean looping, float volume) {
        Music music = musicMap.computeIfAbsent(musicTrack, k -> Gdx.audio.newMusic(Gdx.files.internal(musicTrack.getPath())));
        music.setLooping(looping);
        music.setVolume(volume);
        return music;
    }

    public Sound getSound(SoundTrack soundTrack) {
        return soundMap.computeIfAbsent(soundTrack, k -> Gdx.audio.newSound(Gdx.files.internal(soundTrack.getPath())));
    }
}
