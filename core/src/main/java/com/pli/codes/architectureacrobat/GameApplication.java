package com.pli.codes.architectureacrobat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pli.codes.architectureacrobat.audio.AudioController;
import com.pli.codes.architectureacrobat.audio.MusicTrack;
import com.pli.codes.architectureacrobat.controller.PlayerController;
import com.pli.codes.architectureacrobat.input.InputHandler;
import com.pli.codes.architectureacrobat.level.LevelManager;

public class GameApplication extends ApplicationAdapter {

    private LevelManager levelManager;
    private PlayerController playerController;
    private InputHandler inputHandler;
    private SpriteBatch batch;
    private FitViewport viewport;
    private Texture backgroundTexture;

    @Override
    public void create() {
        viewport = new FitViewport(1920, 1080);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        backgroundTexture = new Texture("background.png");

        batch = new SpriteBatch();
        batch.getProjectionMatrix().scl(Gdx.graphics.getWidth()/1920f, Gdx.graphics.getHeight()/1080f, 1);

        // Initialize the player controller
        playerController = new PlayerController();

        // Initialize input
        inputHandler = new InputHandler(playerController);
        Gdx.input.setInputProcessor(inputHandler);

        // Initialize level manager
        levelManager = new LevelManager(playerController);
        AudioController.getInstance().initialize();
        AudioController.getInstance().getMusic(MusicTrack.BACKGROUND_MUSIC, true, 0.5f).play();
        AudioController.getInstance().getMusic(MusicTrack.BACKGROUND_NOISE, true, 0.25f).play();
    }

    
    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();

        // update all components
        levelManager.getCurrentLevel().update(delta);
        inputHandler.update();
        playerController.update(delta);

        // render all components
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, 1920, 1080);
        levelManager.getCurrentLevel().render(batch, delta);
        if (levelManager.isLastLevel()) {
            levelManager.renderEndScreen(batch);
        }
        playerController.render(batch, delta);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
