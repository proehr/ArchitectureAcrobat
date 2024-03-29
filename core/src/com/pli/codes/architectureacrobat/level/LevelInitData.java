package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.math.Rectangle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pli.codes.architectureacrobat.interaction.Interactable;
import java.util.List;
import lombok.Getter;

@Getter
public class LevelInitData {

    private final float playerStartX;
    private final float playerStartY;
    private final List<Rectangle> platforms;
    private final Target target;
    private final List<Interactable> interactables;

    public LevelInitData(
        @JsonProperty("playerStartX") float playerStartX,
        @JsonProperty("playerStartY") float playerStartY,
        @JsonProperty("platforms") List<Rectangle> platforms,
        @JsonProperty("target") Target target,
        @JsonProperty("interactables") List<Interactable> interactables
    ) {
        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.platforms = platforms;
        this.target = target;
        this.interactables = interactables;
    }

}
