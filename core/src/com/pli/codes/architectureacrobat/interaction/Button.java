package com.pli.codes.architectureacrobat.interaction;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cyphercove.gdxtween.Tweens;
import com.cyphercove.gdxtween.targettweens.Vector2Tween;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pli.codes.architectureacrobat.Tween;
import com.pli.codes.architectureacrobat.animation.AnimationData;
import com.pli.codes.architectureacrobat.audio.AudioController;
import com.pli.codes.architectureacrobat.audio.SoundTrack;
import com.pli.codes.architectureacrobat.level.MovablePlatform;
import com.pli.codes.architectureacrobat.level.Platform;
import java.util.Arrays;
import java.util.List;

public class Button implements Interactable {

    private final Rectangle bounds;
    private final MovablePlatform platform;
    private final Vector2 platformPosition;
    private final Vector2Tween tween;

    private float stateTime = 0;
    private boolean buttonPressed;

    public Button(
        @JsonProperty("bounds") Rectangle bounds,
        @JsonProperty("platform") MovablePlatform platform,
        @JsonProperty("endPosition") Vector2 endPosition
    ) {
        this.bounds = new Rectangle(bounds.x * 32, bounds.y * 32, bounds.width * 32, bounds.height * 32);
        this.platform = platform;
        this.platformPosition = platform.getPosition(new Vector2());
        this.tween = Tweens.to(platformPosition, endPosition.x * 32, endPosition.y * 32).duration(3f);
    }

    @Override
    public void update(float delta) {
        if (buttonPressed) {
            platform.setPosition(platformPosition);
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
        if (!buttonPressed && !tween.isStarted()) {
            buttonPressed = true;
            tween.start(Tween.getInstance());
            AudioController.getInstance().getSound(SoundTrack.BUTTON_CLICK).play();
        }

    }

    @Override
    public List<Platform> getPlatforms() {
        return Arrays.asList(platform);
    }
}
