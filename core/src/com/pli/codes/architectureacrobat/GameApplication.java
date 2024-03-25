package com.pli.codes.architectureacrobat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pli.codes.architectureacrobat.controller.PlayerController;
import com.pli.codes.architectureacrobat.input.InputHandler;
import com.pli.codes.architectureacrobat.level.LevelData;
import java.io.IOException;

public class GameApplication extends ApplicationAdapter {

    private PlayerController playerController;
    private InputHandler inputHandler;
    private SpriteBatch batch;
    private Viewport viewport;
    private Camera camera;
    private Texture backgroundTexture;
    private LevelData currentLevel;

    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(1920, 1080);
        camera = new PerspectiveCamera();
        viewport = new FitViewport(1920, 1080, camera);

        backgroundTexture = new Texture("background.png");
        try {
            currentLevel = new ObjectMapper().readValue(Gdx.files.internal("data/").child("level1.json").read(),
                LevelData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        batch = new SpriteBatch();

        // Initialize the player controller
        playerController = new PlayerController(currentLevel);
        inputHandler = new InputHandler(playerController);
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void resize(int width, int height) {
        width = 1920;
        height = 1080;
        viewport.update(width, height);
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
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        currentLevel.renderPlatforms(batch);
        playerController.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
