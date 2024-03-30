package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class Level implements PropertyChangeListener {

    private final LevelGameData levelData;

    public Level(LevelInitData levelInitData) {
        levelData = new LevelGameData(
            levelInitData.getPlayerStartX(),
            levelInitData.getPlayerStartY(),
            levelInitData.getPlatforms(),
            levelInitData.getTarget(),
            new ArrayList<>(levelInitData.getInteractables())
        );
        levelData.getPlatforms().addAll(
            levelData.getInteractables().stream().flatMap(interactable -> interactable.getPlatforms().stream()).collect(Collectors.toList())
        );
    }

    public void update(float delta) {
        levelData.getTarget().update(delta);
        levelData.getInteractables().forEach(interactable -> interactable.update(delta));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (levelData.getTarget().detectInteraction(evt.getNewValue())) {
            levelData.getTarget().handleInteraction(evt.getNewValue());
        }
        levelData.getInteractables().stream()
            .filter(interactable -> interactable.detectInteraction(evt.getNewValue()))
            .forEach(interactable -> interactable.handleInteraction(evt.getNewValue()));
    }

    public void render(SpriteBatch batch, float delta) {
        levelData.getPlatforms().forEach(platform -> platform.render(batch, delta));
        levelData.getTarget().render(batch, delta);
        levelData.getInteractables().forEach(interactable -> interactable.render(batch, delta));
    }

}
