package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class MovablePlatform extends Platform {

    public MovablePlatform(
        @JsonProperty("x") float x,
        @JsonProperty("y") float y,
        @JsonProperty("width") float width,
        @JsonProperty("height") float height
    ) {
        super(x, y, width, height);
    }

    @Override
    protected Map<Vector2, Texture> prepareTextures(float width, float height) {
        return PlatformUtil.prepareMovablePlatformTextures(width, height);
    }
}
