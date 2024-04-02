package com.pli.codes.architectureacrobat.interaction;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pli.codes.architectureacrobat.level.Platform;
import java.util.List;

public interface Interactable {

    void update(float delta);

    void render(SpriteBatch batch, float delta);

    boolean detectInteraction(Object newValue);

    void handleInteraction(Object newValue);

    List<Platform> getPlatforms();
}
