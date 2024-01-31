package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StandingState implements PlayerState {

    private final PlayerController playerController;

    public StandingState(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void update(float delta) {
        // Logic for standing state update
    }

    @Override
    public void render(SpriteBatch batch) {
        // Render the player using standing texture
        batch.draw(playerController.getStandingTexture(), playerController.getX(),
            playerController.getY());
    }

    @Override
    public void handleInput(int keycode) {
        // Handle input for standing state
        if (keycode == Input.Keys.SPACE) {
            // Transition to jumping state
            playerController.setCurrentState(new JumpingState(playerController));
        } else if (keycode == Input.Keys.DOWN) {
            // Transition to ducking state
            playerController.setCurrentState(new DuckingState(playerController));
        }
    }
}
