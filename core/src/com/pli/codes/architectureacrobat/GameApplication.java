package com.pli.codes.architectureacrobat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pli.codes.architectureacrobat.controller.PlayerController;

public class GameApplication extends ApplicationAdapter {

    private PlayerController playerController;

    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Load your player textures here
        TextureRegion standingTexture = new TextureRegion(new Texture("standing_texture.png"));
        TextureRegion jumpingTexture = new TextureRegion(new Texture("jumping_texture.png"));
        TextureRegion duckingTexture = new TextureRegion(new Texture("ducking_texture.png"));

        // Initialize the player controller
        playerController = new PlayerController(standingTexture, jumpingTexture, duckingTexture);
        Gdx.input.setInputProcessor(playerController);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update and render the player controller
        float delta = Gdx.graphics.getDeltaTime();
        playerController.update(delta);

        batch.begin();
        playerController.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
