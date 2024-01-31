package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JumpingState implements PlayerState {

    private static final float JUMP_VELOCITY = 250F;
    private static final float GRAVITY = -500F;

    private final PlayerController playerController;

    public JumpingState(PlayerController playerController) {
        this.playerController = playerController;
        playerController.setJumping(true);
        playerController.setVelocityY(JUMP_VELOCITY);
    }

    @Override
    public void update(float delta) {
        // Update the player's vertical position based on velocity and gravity
        playerController.setY(playerController.getY() + playerController.getVelocityY() * delta);

        // Apply gravity to simulate downward acceleration
        playerController.setVelocityY(playerController.getVelocityY() + GRAVITY * delta);

        // Check if the player has reached the ground level
        if (playerController.getY() <= 0) {
            playerController.setY(0);
            playerController.setVelocityY(0);
            playerController.setJumping(false);

            // Transition to standing state when landing
            playerController.setCurrentState(new StandingState(playerController));
        }
    }


    @Override
    public void render(SpriteBatch batch) {
        // Render the player using jumping texture
        batch.draw(playerController.getJumpingTexture(), playerController.getX(),
            playerController.getY());
    }

    @Override
    public void handleInput(int keycode) {
        // Handle input for jumping state
    }
}
