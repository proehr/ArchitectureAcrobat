package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.math.Rectangle;
import com.pli.codes.architectureacrobat.interaction.Interactable;
import java.util.List;
import lombok.Getter;

@Getter
public class LevelGameData {

    private final float playerStartX;
    private final float playerStartY;
    private final List<Rectangle> platforms;
    private final Target target;
    private final List<Interactable> interactables;

    public LevelGameData(float playerStartX, float playerStartY, List<Rectangle> platforms, Target target, List<Interactable> interactables) {
        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.platforms = platforms;
        this.target = target;
        this.interactables = interactables;
    }
}
