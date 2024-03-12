package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pli.codes.architectureacrobat.AnimationData;

public class StandingState implements PlayerState {

    private final PlayerController playerController;

    private float stateTime = 0;

    public StandingState(PlayerController playerController) {
        playerController.setMoving(false);
        this.playerController = playerController;
    }

    @Override
    public void update(float delta) {
        // Logic for standing state update
    }

    @Override
    public void render(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = AnimationData.IDLE.getAnimation().getKeyFrame(stateTime, true);
        if (currentFrame.isFlipX() != (playerController.getDirection() == -1)) {
            currentFrame.flip(true, false);
        }
        batch.draw(currentFrame, playerController.getX(), playerController.getY());
    }

    @Override
    public void handleInput(int keycode, boolean isKeyDown) {
        // Handle input for standing state
        if (keycode == Input.Keys.SPACE || keycode == Input.Keys.UP || keycode == Input.Keys.W) {
            // Transition to jumping state
            playerController.setCurrentState(new JumpingState(playerController));
        } else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            // Transition to ducking state
            playerController.setCurrentState(new DuckingState(playerController));
        } else if (isKeyDown && (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)) {
            playerController.setDirection(1);
            // Transition to walking state
            playerController.setCurrentState(new WalkingState(playerController));
        } else if (isKeyDown && (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)) {
            playerController.setDirection(-1);
            // Transition to walking state
            playerController.setCurrentState(new WalkingState(playerController));
        }
    }
}
