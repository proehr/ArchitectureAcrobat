package com.pli.codes.architectureacrobat.interaction;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.pli.codes.architectureacrobat.animation.AnimationData;
import com.pli.codes.architectureacrobat.audio.AudioController;
import com.pli.codes.architectureacrobat.audio.SoundTrack;
import com.pli.codes.architectureacrobat.level.Platform;
import com.pli.codes.architectureacrobat.observer.PropertyChangeEvent;
import java.util.Collections;
import java.util.List;
import lombok.Getter;


public class Target implements Interactable {

    private static final Texture TARGET_TOP_LEFT_PLATFORM = new Texture("platforms/IndustrialTile_58.png");
    private static final Texture TARGET_TOP_RIGHT_PLATFORM = new Texture("platforms/IndustrialTile_60.png");


    @Getter
    private PropertyChangeEvent onTargetDoorOpened = new PropertyChangeEvent(this);
    @Getter
    private final Rectangle bounds;

    private boolean targetReached;
    private float stateTime = 0;


    public Target(InteractableData interactableData) {
        this.bounds = new Rectangle(
            interactableData.getBounds().x * 32,
            interactableData.getBounds().y * 32,
            interactableData.getBounds().width * 32,
            interactableData.getBounds().height * 32
        );
    }

    @Override
    public void update(float delta) {

    }

    public void render(SpriteBatch batch, float delta) {
        if (targetReached) {
            stateTime += delta;
        }
        if (stateTime >= AnimationData.TARGET_OPEN.getAnimation().getAnimationDuration() && targetReached) {
            AudioController.getInstance().getSound(SoundTrack.SUCCESS).stop();
            onTargetDoorOpened.notifyObservers("levelComplete", false, true);
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

    public void handleInteraction(Object newValue) {
        if (!targetReached) {
            targetReached = true;
            AudioController.getInstance().getSound(SoundTrack.SUCCESS).play();
        }
    }

    @Override
    public List<Platform> getPlatforms() {
        return Collections.emptyList();
    }

}
