package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.math.Rectangle;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;

@Getter
public class LevelData {

    private final float playerStartX;
    private final float playerStartY;
    private final Target target;
    private final List<Rectangle> platforms;

    public LevelData(
        @JsonProperty("playerStartX") float playerStartX,
        @JsonProperty("playerStartY") float playerStartY,
        @JsonProperty("target") Rectangle target,
        @JsonProperty("platforms") List<Rectangle> platforms
    ) {
        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.target = new Target(target);
        this.platforms = platforms;
    }

}
