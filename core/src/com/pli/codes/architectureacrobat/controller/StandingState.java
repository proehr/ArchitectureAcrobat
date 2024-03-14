package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.Gdx;
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
    }
}
