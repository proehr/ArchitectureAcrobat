package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pli.codes.architectureacrobat.AnimationData;

public class JumpingState implements PlayerState {

    private static final float JUMP_VELOCITY = 250F;
    private static final float GRAVITY = -500F;

    private final PlayerController playerController;

    private float stateTimeJump = 0;
    private float stateTimeFall = 0;

    public JumpingState(PlayerController playerController) {
        this.playerController = playerController;
        playerController.setJumping(true);
        playerController.setVelocityY(JUMP_VELOCITY);
    }

    @Override
    public void update(float delta) {
        // Update the player's vertical position based on velocity and gravity
        if (stateTimeJump >= AnimationData.FRAME_DURATION) {
            playerController.setY(playerController.getY() + playerController.getVelocityY() * delta);

            // Apply gravity to simulate downward acceleration
            playerController.setVelocityY(playerController.getVelocityY() + GRAVITY * delta);

            // Check if the player has reached the ground level
            if (playerController.getY() <= 0) {
                playerController.setY(0);
                playerController.setVelocityY(0);
                playerController.setJumping(false);

                // Transition to standing state when landing
                playerController.setCurrentState(
                    playerController.isMoving() ? new WalkingState(playerController) : new StandingState(playerController)
                );
            }
        }
        if (playerController.isMoving()) {
            float newX = playerController.getX() + WalkingState.WALK_SPEED * delta * playerController.getDirection();
            if (newX >= 0 && newX <= Gdx.graphics.getWidth()) {
                playerController.setX(newX);
            }
        }
    }


    @Override
    public void render(SpriteBatch batch) {

        TextureRegion currentFrame;
        if (playerController.getVelocityY() >= 0) {
            stateTimeJump += Gdx.graphics.getDeltaTime();
            currentFrame = AnimationData.JUMP.getAnimation().getKeyFrame(stateTimeJump, false);
        } else {
            stateTimeFall += Gdx.graphics.getDeltaTime();
            currentFrame = AnimationData.FALL.getAnimation().getKeyFrame(stateTimeFall, false);
        }
        if (currentFrame.isFlipX() != (playerController.getDirection() == -1)) {
            currentFrame.flip(true, false);
        }
        batch.draw(currentFrame, playerController.getX(), playerController.getY());
    }

    @Override
    public void handleInput(int keycode, boolean isKeyDown) {

    }
}
