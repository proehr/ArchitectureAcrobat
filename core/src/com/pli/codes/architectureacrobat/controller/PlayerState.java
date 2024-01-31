package com.pli.codes.architectureacrobat.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface PlayerState {

    void update(float delta);

    void render(SpriteBatch batch);

    void handleInput(int keycode);
}
