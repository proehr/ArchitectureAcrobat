package com.pli.codes.architectureacrobat.interaction;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pli.codes.architectureacrobat.animation.AnimationData;
import com.pli.codes.architectureacrobat.audio.AudioController;
import com.pli.codes.architectureacrobat.audio.SoundTrack;
import com.pli.codes.architectureacrobat.level.MovablePlatform;
import com.pli.codes.architectureacrobat.level.Platform;
import java.util.Arrays;
import java.util.List;

public class Button implements Interactable {

    private final Rectangle bounds;
    private final Platform platform;
    private final Vector2 startPosition;
    private final Vector2 targetPosition;

    private float stateTime = 0;
    private boolean buttonPressed;

    public Button(ButtonData buttonData) {
        this.bounds = new Rectangle(buttonData.getBounds().x * 32, buttonData.getBounds().y * 32, buttonData.getBounds().width * 32,
            buttonData.getBounds().height * 32);
        this.platform = new MovablePlatform(buttonData.getPlatform());
        this.startPosition = platform.getBounds().getPosition(new Vector2());
        this.targetPosition = new Vector2(buttonData.getEndPosX() * 32, buttonData.getEndPosY() * 32);
    }

    @Override
    public void update(float delta) {
        if (buttonPressed && stateTime < 3f) {
            float alpha = Math.min(1.0f, stateTime / 3f);
            float invAlpha = 1.0f - alpha;
            platform.getBounds().x = (startPosition.x * invAlpha) + (targetPosition.x * alpha);
            platform.getBounds().y = (startPosition.y * invAlpha) + (targetPosition.y * alpha);
        }
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        if (buttonPressed) {
            stateTime += delta;
        }
        TextureRegion currentFrame = AnimationData.BUTTON_PRESS.getAnimation().getKeyFrame(stateTime, false);
        batch.draw(currentFrame, bounds.x, bounds.y, 32, 32);

        platform.render(batch, delta);
    }

    @Override
    public boolean detectInteraction(Object newValue) {
        if (newValue instanceof Rectangle) {
            Rectangle playerBounds = (Rectangle) newValue;
            return bounds.overlaps(playerBounds);
        }
        return false;
    }

    @Override
    public void handleInteraction(Object newValue) {
        if (!buttonPressed) {
            buttonPressed = true;
            AudioController.getInstance().getSound(SoundTrack.BUTTON_CLICK).play();
        }

    }

    @Override
    public List<Platform> getPlatforms() {
        return Arrays.asList(platform);
    }
}
