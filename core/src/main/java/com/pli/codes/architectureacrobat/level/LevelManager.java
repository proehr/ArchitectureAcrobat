package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.pli.codes.architectureacrobat.controller.PlayerController;
import com.pli.codes.architectureacrobat.observer.Observer;
import lombok.Getter;

public class LevelManager implements Observer {

    private static final Json MAPPER = new Json();
    private final PlayerController playerController;
    private final EndScreen endScreen = new EndScreen();

    @Getter
    private Level currentLevel;

    private int currentLevelIndex = 0;

    public LevelManager(PlayerController playerController) {
        this.playerController = playerController;
        loadLevel(LevelName.values()[currentLevelIndex].getFileName());
    }

    private void loadNextLevel() {
        if (currentLevelIndex == LevelName.values().length - 1) {
            Gdx.app.exit();
            return;
        }
        currentLevelIndex++;
        loadLevel(LevelName.values()[currentLevelIndex].getFileName());
    }

    private void loadLevel(String levelName) {
        LevelInitData levelInitData = MAPPER.fromJson(LevelInitData.class, Gdx.files.internal("data/").child(levelName + ".json"));
        currentLevel = new Level(levelInitData);
        playerController.reset(
            currentLevel.getLevelData().getPlayerStartX(),
            currentLevel.getLevelData().getPlayerStartY(),
            PlatformUtil.getPlatformBounds(currentLevel.getLevelData().getPlatforms())
        );
        playerController.getOnPositionChange().register(currentLevel);
        currentLevel.getLevelData().getTarget().getOnTargetDoorOpened().register(this);

    }

    public boolean isLastLevel() {
        return currentLevelIndex == LevelName.values().length - 1;
    }

    public void renderEndScreen(SpriteBatch batch) {
        endScreen.render(batch);
    }

    @Override
    public void update(String paramName, Object oldValue, Object newValue) {
        if (paramName.equals("levelComplete")) {
            loadNextLevel();
        }
    }

    @Getter
    private enum LevelName {
        LEVEL0("level0"),
        LEVEL1("level1"),
        FINAL_LEVEL("final_level");
        private final String fileName;

        LevelName(String fileName) {
            this.fileName = fileName;
        }
    }

}
