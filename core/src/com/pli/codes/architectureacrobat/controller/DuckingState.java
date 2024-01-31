package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DuckingState implements PlayerState {

    private final PlayerController playerController;

    public DuckingState(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void update(float delta) {
        // Logic for ducking state update
    }

    @Override
    public void render(SpriteBatch batch) {
        // Render the player using ducking texture
        batch.draw(playerController.getDuckingTexture(), playerController.getX(),
            playerController.getY());
    }

    @Override
    public void handleInput(int keycode) {
        // Handle input for ducking state
        if (keycode == Input.Keys.UP) {
            // Transition back to standing state
            playerController.setCurrentState(new StandingState(playerController));
        }
    }
}
