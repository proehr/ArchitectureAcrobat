package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.pli.codes.architectureacrobat.level.LevelData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerController {

    public static final float GRAVITY = -1000F;

    private PlayerState currentState;
    private LevelData currentLevel;

    // Player position
    private Rectangle characterBounds;
    // Vertical velocity
    private float velocityY;
    private int direction;
    private boolean moving;
    private boolean isJumping;

    public PlayerController(LevelData currentLevel) {
        this.characterBounds = new Rectangle(currentLevel.getPlayerStartX(), currentLevel.getPlayerStartY(), 50, 63);
        this.currentLevel = currentLevel;
        currentState = new StandingState(this);
    }

    public void update(float delta) {
        currentState.update(delta);
    }

    public void render(SpriteBatch batch) {
        currentState.render(batch);
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
        for (Rectangle rectangle : currentLevel.getScaledPlatforms()) {
            if (characterBounds.overlaps(rectangle)) {
                if (direction < 0) {
                    characterBounds.x = (int) (rectangle.x + rectangle.width + 0.01f);
                } else {
                    characterBounds.x = rectangle.x - characterBounds.width - 0.01f;
                }
            }
        }
    }

    public void setY(float y) {
        characterBounds.y = y;
        for (Rectangle rectangle : currentLevel.getScaledPlatforms()) {
            if (characterBounds.overlaps(rectangle)) {
                if (velocityY < 0) {
                    characterBounds.y = (int) (rectangle.y + rectangle.height + 0.01f);
                    isJumping = false;
                } else {
                    characterBounds.y = rectangle.y - characterBounds.height - 0.01f;
                }
                velocityY = 0;
            }
        }
    }
}
