package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pli.codes.architectureacrobat.AnimationData;

public class JumpingState implements PlayerState {

    private static final float JUMP_VELOCITY = 550F;

    private final PlayerController playerController;

    private float stateTimeJump = 0;
    private float stateTimeFall = 0;

    public JumpingState(PlayerController playerController, boolean jumpPressed) {
        this.playerController = playerController;
        playerController.setJumping(true);
        if (jumpPressed) {
            playerController.setVelocityY(JUMP_VELOCITY);
        } else {
            stateTimeJump = AnimationData.JUMP.getAnimation().getFrameDuration();
        }
    }

    @Override
    public void update(float delta) {
        playerController.setVelocityY(playerController.getVelocityY() + PlayerController.GRAVITY * delta);
        playerController.setY(
            playerController.getCharacterBounds().getY() + playerController.getVelocityY() * delta
        );

        // Check if the player has reached the ground level
        if (!playerController.isJumping()) {
            playerController.setVelocityY(0);
            playerController.setJumping(false);

            // Transition to standing state when landing
            playerController.setCurrentState(
                playerController.isMoving() ? new WalkingState(playerController)
                    : new StandingState(playerController)
            );
        }

        if (playerController.isMoving()) {
            float newX = playerController.getCharacterBounds().getX()
                + WalkingState.WALK_SPEED * delta * playerController.getDirection();
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
        batch.draw(currentFrame, playerController.getCharacterBounds().getX() - 25,
            playerController.getCharacterBounds().getY(), 100, 74);
    }

    @Override
    public void handleInput(int keycode, boolean isKeyDown) {

    }
}
