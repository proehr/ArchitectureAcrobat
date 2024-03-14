package com.pli.codes.architectureacrobat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pli.codes.architectureacrobat.controller.PlayerController;
import com.pli.codes.architectureacrobat.input.InputHandler;

public class GameApplication extends ApplicationAdapter {

    private PlayerController playerController;
    private InputHandler inputHandler;

    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Initialize the player controller
        playerController = new PlayerController();
        inputHandler = new InputHandler(playerController);
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update and render the player controller
        float delta = Gdx.graphics.getDeltaTime();
        inputHandler.update();
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
