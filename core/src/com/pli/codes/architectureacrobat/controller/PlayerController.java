package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerController {

    public static final float GRAVITY = -1000F;
    private List<? extends Rectangle> platforms;

    @Getter
    private PropertyChangeSupport onPositionChange = new PropertyChangeSupport(this);

    private PlayerState currentState;

    // Player position
    private Rectangle characterBounds;
    // Vertical velocity
    private float velocityY;
    private int direction;
    private boolean moving;
    private boolean isJumping;

    public PlayerController() {
        this.characterBounds = new Rectangle(0, 0, 50, 63);
        this.currentState = new StandingState(this);
    }

    public void reset(float startX, float startY, List<? extends Rectangle> platforms) {
        Arrays.stream(onPositionChange.getPropertyChangeListeners()).forEach(onPositionChange::removePropertyChangeListener);
        this.platforms = platforms;
        this.characterBounds.x = startX;
        this.characterBounds.y = startY;
        this.currentState = new StandingState(this);
    }

    public void update(float delta) {
        currentState.update(delta);
    }

    public void render(SpriteBatch batch, float delta) {
        currentState.render(batch, delta);
    }

    public void jump() {
        if (canJump()) {
            currentState = new JumpingState(this, true);
        }
    }

    private boolean canJump() {
        return !isJumping;
    }

    public void moveLeft() {
        if (canMove()) {
            direction = -1;
            moving = true;
            if (!isJumping && currentState instanceof StandingState) {
                currentState = new WalkingState(this);
            }
        }
    }

    public void moveRight() {
        if (canMove()) {
            direction = 1;
            moving = true;
            if (!isJumping && currentState instanceof StandingState) {
                currentState = new WalkingState(this);
            }
        }
    }

    private boolean canMove() {
        return true;
    }

    public void stopMove() {
        moving = false;
        if (currentState instanceof WalkingState) {
            currentState = new StandingState(this);
        }
    }

    public void setX(float x) {
        characterBounds.x = x;
        for (Rectangle rectangle : platforms) {
            if (characterBounds.overlaps(rectangle)) {
                if (direction < 0) {
                    characterBounds.x = rectangle.x + rectangle.width + 0.01f;
                } else {
                    characterBounds.x = rectangle.x - characterBounds.width - 0.01f;
                }
            }
        }
        onPositionChange.firePropertyChange("posChanged", null, characterBounds);
    }

    public void setY(float y) {
        characterBounds.y = y;
        for (Rectangle rectangle : platforms) {
            if (characterBounds.overlaps(rectangle)) {
                if (velocityY < 0) {
                    characterBounds.y = rectangle.y + rectangle.height + 0.01f;
                    isJumping = false;
                } else {
                    characterBounds.y = rectangle.y - characterBounds.height - 0.01f;
                }
                velocityY = 0;
            }
        }
        onPositionChange.firePropertyChange("posChanged", null, characterBounds);
    }
}
