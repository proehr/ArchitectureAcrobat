package com.pli.codes.architectureacrobat.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.pli.codes.architectureacrobat.controller.PlayerController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputHandler extends InputAdapter {

    private final PlayerController playerController;
    private final Map<Integer, Command<PlayerController>> keyDownMappings = new HashMap<Integer, Command<PlayerController>>();
    private final Map<Integer, Command<PlayerController>> keyUpMapping = new HashMap<Integer, Command<PlayerController>>();
    private final Map<Integer, Command<PlayerController>> keyPressedMappings = new HashMap<Integer, Command<PlayerController>>();
    private final List<Integer> pressedKeys = new ArrayList<Integer>();

    private final Command<PlayerController> jumpCommand = new Command<PlayerController>() {
        @Override
        public void execute(PlayerController playerController) {
            playerController.jump();
        }
    };

    private final Command<PlayerController> moveRightCommand = new Command<PlayerController>() {
        @Override
        public void execute(PlayerController playerController) {
            playerController.moveRight();
        }
    };

    private final Command<PlayerController> moveLeftCommand = new Command<PlayerController>() {
        @Override
        public void execute(PlayerController playerController) {
            playerController.moveLeft();
        }
    };

    private final Command<PlayerController> stopMoveCommand = new Command<PlayerController>() {
        @Override
        public void execute(PlayerController playerController) {
            playerController.stopMove();
        }
    };

    public InputHandler(PlayerController playerController) {
        this.playerController = playerController;

        keyDownMappings.put(Input.Keys.SPACE, jumpCommand);
        keyDownMappings.put(Input.Keys.UP, jumpCommand);
        keyDownMappings.put(Input.Keys.W, jumpCommand);

        keyPressedMappings.put(Input.Keys.RIGHT, moveRightCommand);
        keyPressedMappings.put(Input.Keys.D, moveRightCommand);
        keyPressedMappings.put(Input.Keys.LEFT, moveLeftCommand);
        keyPressedMappings.put(Input.Keys.A, moveLeftCommand);

        keyUpMapping.put(Input.Keys.RIGHT, stopMoveCommand);
        keyUpMapping.put(Input.Keys.D, stopMoveCommand);
        keyUpMapping.put(Input.Keys.LEFT, stopMoveCommand);
        keyUpMapping.put(Input.Keys.A, stopMoveCommand);
    }

    @Override
    public boolean keyDown(int keycode) {
        Command<PlayerController> playerControllerCommand = keyDownMappings.get(keycode);
        if (playerControllerCommand != null) {
            playerControllerCommand.execute(playerController);
        }
        pressedKeys.add(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        Command<PlayerController> playerControllerCommand = keyUpMapping.get(keycode);
        if (playerControllerCommand != null) {
            playerControllerCommand.execute(playerController);
        }
        pressedKeys.remove((Integer) keycode);
        return true;
    }

    public void update() {
        for (Integer keycode : pressedKeys) {
            Command<PlayerController> playerControllerCommand = keyPressedMappings.get(keycode);
            if (playerControllerCommand != null) {
                playerControllerCommand.execute(playerController);
            }
        }
    }
}
