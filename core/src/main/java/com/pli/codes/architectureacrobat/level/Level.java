package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pli.codes.architectureacrobat.interaction.Interactable;
import com.pli.codes.architectureacrobat.observer.Observer;
import lombok.Getter;

@Getter
public class Level implements Observer {

    private final LevelGameData levelData;

    public Level(LevelInitData levelInitData) {
        levelData = new LevelGameData(
            levelInitData.getPlayerStartX(),
            levelInitData.getPlayerStartY(),
            LevelBuildUtil.buildPlatforms(levelInitData.getPlatforms()),
            LevelBuildUtil.buildTarget(levelInitData.getTarget()),
            LevelBuildUtil.buildInteractables(levelInitData.getInteractables())
        );

        for (Interactable interactable : levelData.getInteractables()) {
            for (Platform rectangle : interactable.getPlatforms()) {
                levelData.getPlatforms().add(rectangle);
            }
        }
    }

    public void update(float delta) {
        levelData.getTarget().update(delta);
        for (Interactable interactable : levelData.getInteractables()) {
            interactable.update(delta);
        }
    }

    public void render(SpriteBatch batch, float delta) {
        for (Platform platform : levelData.getPlatforms()) {
            platform.render(batch, delta);
        }
        levelData.getTarget().render(batch, delta);
        for (Interactable interactable : levelData.getInteractables()) {
            interactable.render(batch, delta);
        }
    }

    @Override
    public void update(String paramName, Object oldValue, Object newValue) {
        if (levelData.getTarget().detectInteraction(newValue)) {
            levelData.getTarget().handleInteraction(newValue);
        }
        for (Interactable interactable : levelData.getInteractables()) {
            if (interactable.detectInteraction(newValue)) {
                interactable.handleInteraction(newValue);
            }
        }
    }
}
