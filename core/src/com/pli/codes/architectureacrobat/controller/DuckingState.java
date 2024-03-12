package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pli.codes.architectureacrobat.AnimationData;

public class DuckingState implements PlayerState {

    private final PlayerController playerController;

    private float stateTime = 0;

    public DuckingState(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void update(float delta) {
        // Logic for ducking state update
    }

    @Override
    public void render(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = AnimationData.CROUCH.getAnimation().getKeyFrame(stateTime, true);
        if (currentFrame.isFlipX() != (playerController.getDirection() == -1)) {
            currentFrame.flip(true, false);
        }
        batch.draw(currentFrame, playerController.getX(), playerController.getY());
    }

    @Override
    public void handleInput(int keycode, boolean isKeyDown) {
        // Handle input for ducking state
        if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
            // Transition back to standing state
            playerController.setCurrentState(new StandingState(playerController));
        }
    }
}
