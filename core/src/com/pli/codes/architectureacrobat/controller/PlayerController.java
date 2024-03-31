package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class PlayerController {

    public static final float GRAVITY = -1000F;
    @Getter
    @Setter
    private float velocityY;
    @Getter
    @Setter
    private boolean moving;
    @Getter
    @Setter
    private boolean jumping;

    @Getter
    private Rectangle characterBounds;
    @Getter
    private int direction;

    @Getter
    private PropertyChangeSupport onPositionChange = new PropertyChangeSupport(this);

    private List<? extends Rectangle> platforms;
    private PlayerState currentState;

    public PlayerController() {
        this.characterBounds = new Rectangle(0, 0, 50, 63);
        this.currentState = new StandingState(this);
        this.currentState.onEnter();
    }

    public void reset(float startX, float startY, List<? extends Rectangle> platforms) {
        Arrays.stream(onPositionChange.getPropertyChangeListeners()).forEach(onPositionChange::removePropertyChangeListener);
        this.platforms = platforms;
        this.characterBounds.x = startX;
        this.characterBounds.y = startY;
        setCurrentState(new StandingState(this));
    }

    public void update(float delta) {
        currentState.update(delta);
    }

    public void render(SpriteBatch batch, float delta) {
        currentState.render(batch, delta);
    }

    public void setCurrentState(PlayerState currentState) {
        this.currentState.onExit();
        this.currentState = currentState;
        this.currentState.onEnter();
    }

    public void jump() {
        if (canJump()) {
            setCurrentState(new JumpingState(this, true));
        }
    }

    private boolean canJump() {
        return !jumping;
    }

    public void moveLeft() {
        if (canMove()) {
            direction = -1;
            moving = true;
            if (!jumping && currentState instanceof StandingState) {
                setCurrentState(new WalkingState(this));
            }
        }
    }

    public void moveRight() {
        if (canMove()) {
            direction = 1;
            moving = true;
            if (!jumping && currentState instanceof StandingState) {
                setCurrentState(new WalkingState(this));
            }
        }
    }

    private boolean canMove() {
        return true;
    }

    public void stopMove() {
        moving = false;
        if (currentState instanceof WalkingState) {
            setCurrentState(new StandingState(this));
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
                    jumping = false;
                } else {
                    characterBounds.y = rectangle.y - characterBounds.height - 0.01f;
                }
                velocityY = 0;
            }
        }
        onPositionChange.firePropertyChange("posChanged", null, characterBounds);
    }
}
