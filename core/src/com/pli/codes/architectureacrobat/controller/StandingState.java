package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pli.codes.architectureacrobat.animation.AnimationData;

public class StandingState implements PlayerState {

    private final PlayerController playerController;

    private float stateTime = 0;

    public StandingState(PlayerController playerController) {
        playerController.setMoving(false);
        this.playerController = playerController;
    }

    @Override
    public void update(float delta) {
        playerController.setVelocityY(playerController.getVelocityY() + PlayerController.GRAVITY * delta);
        playerController.setY(playerController.getCharacterBounds().getY() + playerController.getVelocityY() * delta);
        if (playerController.getVelocityY() < 0) {
            playerController.setCurrentState(new JumpingState(playerController, false));
        }
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        stateTime += delta;
        TextureRegion currentFrame = AnimationData.IDLE.getAnimation().getKeyFrame(stateTime, true);
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
