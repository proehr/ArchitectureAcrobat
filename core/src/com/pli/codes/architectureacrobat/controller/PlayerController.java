package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerController extends InputAdapter {

    private PlayerState currentState;
    private final PlayerState standingState;
    private final PlayerState jumpingState;
    private final PlayerState duckingState;

    // Player position
    private float x, y;
    // Vertical velocity
    private float velocityY;
    private int direction;
    private boolean moving;
    private boolean isJumping;
    private boolean isDucking;

    private TextureRegion standingTexture;
    private TextureRegion jumpingTexture;
    private TextureRegion duckingTexture;

    public PlayerController(TextureRegion standingTexture, TextureRegion jumpingTexture, TextureRegion duckingTexture) {
        this.standingTexture = standingTexture;
        this.jumpingTexture = jumpingTexture;
        this.duckingTexture = duckingTexture;

        standingState = new StandingState(this);
        jumpingState = new JumpingState(this);
        duckingState = new DuckingState(this);

        currentState = standingState;
    }

    public void update(float delta) {
        currentState.update(delta);
    }

    public void render(SpriteBatch batch) {
        currentState.render(batch);
    }

    @Override
    public boolean keyDown(int keycode) {
        currentState.handleInput(keycode, true);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        currentState.handleInput(keycode, false);
        return true;
    }
}
