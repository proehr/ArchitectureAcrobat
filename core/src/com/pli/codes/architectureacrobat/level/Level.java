package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.pli.codes.architectureacrobat.interaction.Interactable;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Map;
import lombok.Getter;

public class Level implements PropertyChangeListener {

    @Getter
    private LevelGameData levelData;
    private final Map<Vector2, Texture> platformTextures;


    public Level(LevelInitData levelInitData) {
        platformTextures = PlatformUtil.preparePlatformTextures(levelInitData.getPlatforms());
        levelData = new LevelGameData(
            levelInitData.getPlayerStartX(),
            levelInitData.getPlayerStartY(),
            PlatformUtil.prepareScaledPlatforms(levelInitData.getPlatforms()),
            levelInitData.getTarget(),
            new ArrayList<>(levelInitData.getInteractables())
        );
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (levelData.getTarget().detectInteraction(evt.getNewValue())) {
            levelData.getTarget().handleInteraction();
        }
        levelData.getInteractables().stream()
            .filter(interactable -> interactable.detectInteraction(evt.getNewValue()))
            .forEach(Interactable::handleInteraction);
    }

    public void render(SpriteBatch batch, float delta) {
        renderPlatforms(batch);
        levelData.getTarget().render(batch, delta);
        levelData.getInteractables().forEach(interactable -> interactable.render(batch, delta));
    }

    private void renderPlatforms(SpriteBatch batch) {
        for (Map.Entry<Vector2, Texture> entry : platformTextures.entrySet()) {
            batch.draw(entry.getValue(), entry.getKey().x, entry.getKey().y);
        }
    }


}
