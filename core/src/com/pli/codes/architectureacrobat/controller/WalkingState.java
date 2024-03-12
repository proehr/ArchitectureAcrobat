package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pli.codes.architectureacrobat.AnimationData;

public class WalkingState implements PlayerState {

    //TODO: write config into a class/config file
    public static final float WALK_SPEED = 150f; // Adjust as needed

    private final PlayerController playerController;

    private float stateTime = 0;

    public WalkingState(PlayerController playerController) {
        playerController.setMoving(true);
        this.playerController = playerController;
    }

    @Override
    public void update(float delta) {
        // Update the player's horizontal position based on walking speed
        float newX = playerController.getX() + WALK_SPEED * delta * playerController.getDirection();
        // Ensure the player stays within the screen bounds
        if (newX >= 0 && newX <= Gdx.graphics.getWidth()) {
            playerController.setX(newX);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = AnimationData.RUN.getAnimation().getKeyFrame(stateTime, true);
        if (currentFrame.isFlipX() != (playerController.getDirection() == -1)) {
            currentFrame.flip(true, false);
        }
        batch.draw(currentFrame, playerController.getX(), playerController.getY());
    }

    @Override
    public void handleInput(int keycode, boolean isKeyDown) {
        if (!isKeyDown && playerController.getDirection() == 1 && (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)) {
            playerController.setCurrentState(new StandingState(playerController));
        } else if (!isKeyDown && playerController.getDirection() == -1 && (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)) {
            // Transition to walking state
            playerController.setCurrentState(new StandingState(playerController));
        } else if (isKeyDown && (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)) {
            playerController.setDirection(1);
            // Transition to walking state
            playerController.setCurrentState(new WalkingState(playerController));
        } else if (isKeyDown && (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)) {
            playerController.setDirection(-1);
            // Transition to walking state
            playerController.setCurrentState(new WalkingState(playerController));
        } else if (isKeyDown && (keycode == Input.Keys.SPACE || keycode == Input.Keys.UP || keycode == Input.Keys.W)) {
            // Transition to jumping state
            playerController.setCurrentState(new JumpingState(playerController));
        }
    }
}
