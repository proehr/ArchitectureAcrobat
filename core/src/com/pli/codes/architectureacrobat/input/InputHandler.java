package com.pli.codes.architectureacrobat.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.pli.codes.architectureacrobat.controller.PlayerController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class InputHandler extends InputAdapter {

    private final PlayerController playerController;
    private final Map<Integer, Consumer<PlayerController>> keyDownMappings = new HashMap<>();
    private final Map<Integer, Consumer<PlayerController>> keyUpMapping = new HashMap<>();
    private final Map<Integer, Consumer<PlayerController>> keyPressedMappings = new HashMap<>();
    private final List<Integer> pressedKeys = new ArrayList<>();

    public InputHandler(PlayerController playerController) {
        this.playerController = playerController;

        keyDownMappings.put(Input.Keys.SPACE, PlayerController::jump);
        keyDownMappings.put(Input.Keys.UP, PlayerController::jump);
        keyDownMappings.put(Input.Keys.W, PlayerController::jump);

        keyPressedMappings.put(Input.Keys.RIGHT, PlayerController::moveRight);
        keyPressedMappings.put(Input.Keys.D, PlayerController::moveRight);
        keyPressedMappings.put(Input.Keys.LEFT, PlayerController::moveLeft);
        keyPressedMappings.put(Input.Keys.A, PlayerController::moveLeft);

        keyUpMapping.put(Input.Keys.RIGHT, PlayerController::stopMove);
        keyUpMapping.put(Input.Keys.D, PlayerController::stopMove);
        keyUpMapping.put(Input.Keys.LEFT, PlayerController::stopMove);
        keyUpMapping.put(Input.Keys.A, PlayerController::stopMove);
    }

    @Override
    public boolean keyDown(int keycode) {
        keyDownMappings.getOrDefault(keycode, pc -> {
        }).accept(playerController);
        pressedKeys.add(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        keyUpMapping.getOrDefault(keycode, pc -> {
        }).accept(playerController);
        pressedKeys.remove((Integer) keycode);
        return true;
    }

    public void update() {
        for (Integer keycode : pressedKeys) {
            keyPressedMappings.getOrDefault(keycode, pc -> {
            }).accept(playerController);
        }
    }
}
