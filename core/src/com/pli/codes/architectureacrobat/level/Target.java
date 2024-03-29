package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pli.codes.architectureacrobat.animation.AnimationData;
import com.pli.codes.architectureacrobat.interaction.Interactable;
import java.beans.PropertyChangeSupport;
import lombok.Getter;


public class Target extends Interactable {

    private static final Texture TARGET_TOP_LEFT_PLATFORM = new Texture("platforms/IndustrialTile_58.png");
    private static final Texture TARGET_TOP_RIGHT_PLATFORM = new Texture("platforms/IndustrialTile_60.png");


    @Getter
    private PropertyChangeSupport onTargetDoorOpened = new PropertyChangeSupport(this);
    @Getter
    private final Rectangle bounds;

    private boolean targetReached;
    private float stateTime = 0;


    public Target(@JsonProperty("bounds") Rectangle bounds) {
        this.bounds = new Rectangle(bounds.x * 32, bounds.y * 32, bounds.width * 32, bounds.height * 32);
    }

    public void render(SpriteBatch batch, float delta) {
        if (targetReached) {
            stateTime += delta;
        }
        if (stateTime >= AnimationData.TARGET_OPEN.getAnimation().getAnimationDuration() && targetReached) {
            onTargetDoorOpened.firePropertyChange("levelComplete", false, true);
        }
        TextureRegion currentFrame = AnimationData.TARGET_OPEN.getAnimation().getKeyFrame(stateTime, false);
        batch.draw(currentFrame, bounds.x, bounds.y);
        batch.draw(currentFrame, (bounds.x + 32), bounds.y);
        batch.draw(TARGET_TOP_LEFT_PLATFORM, bounds.x, (bounds.y + 64));
        batch.draw(TARGET_TOP_RIGHT_PLATFORM, (bounds.x + 32), (bounds.y + 64));
    }

    public boolean detectInteraction(Object newValue) {
        if (newValue instanceof Rectangle) {
            Rectangle playerBounds = (Rectangle) newValue;
            return bounds.overlaps(playerBounds);
        }
        return false;
    }

    public void handleInteraction() {
        targetReached = true;
    }

}
