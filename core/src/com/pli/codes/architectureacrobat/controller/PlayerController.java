package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerController {

    private PlayerState currentState;

    // Player position
    private float x, y;
    // Vertical velocity
    private float velocityY;
    private int direction;
    private boolean moving;
    private boolean isJumping;

    public PlayerController() {
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
            currentState = new JumpingState(this);
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
}
