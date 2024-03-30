package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.Gdx;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pli.codes.architectureacrobat.controller.PlayerController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import lombok.Getter;

public class LevelManager implements PropertyChangeListener {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final PlayerController playerController;

    @Getter
    private Level currentLevel;

    private int currentLevelIndex = 1;

    public LevelManager(PlayerController playerController) {
        this.playerController = playerController;
        loadLevel(LevelName.values()[currentLevelIndex].getFileName());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("levelComplete")) {
            loadNextLevel();
        }
    }

    private void loadNextLevel() {
        currentLevelIndex++;
        if (currentLevelIndex >= LevelName.values().length) {
            Gdx.app.exit();
            System.exit(-1);
        }
        loadLevel(LevelName.values()[currentLevelIndex].getFileName());
    }

    private void loadLevel(String levelName) {
        try {
            LevelInitData levelInitData = MAPPER.readValue(Gdx.files.internal("data/").child(levelName + ".json").read(),
                LevelInitData.class);
            currentLevel = new Level(levelInitData);
            playerController.reset(
                currentLevel.getLevelData().getPlayerStartX(),
                currentLevel.getLevelData().getPlayerStartY(),
                currentLevel.getLevelData().getPlatforms()
            );
            playerController.getOnPositionChange().addPropertyChangeListener(currentLevel);
            currentLevel.getLevelData().getTarget().getOnTargetDoorOpened().addPropertyChangeListener(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Getter
    public enum LevelName {
        LEVEL0("level0"),
        LEVEL1("level1"),
        ;
        private final String fileName;

        LevelName(String fileName) {
            this.fileName = fileName;
        }
    }

}
