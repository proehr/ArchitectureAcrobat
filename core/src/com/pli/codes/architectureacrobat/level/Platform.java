package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class Platform extends Rectangle {

    protected final Map<Vector2, Texture> textures;

    public Platform(
        @JsonProperty("x") float x,
        @JsonProperty("y") float y,
        @JsonProperty("width") float width,
        @JsonProperty("height") float height
    ) {
        super(x * 32, y * 32, width * 32, height * 32);
        textures = prepareTextures(width, height);
    }

    protected Map<Vector2, Texture> prepareTextures(float width, float height) {
        return PlatformUtil.preparePlatformTextures(width, height);
    }

    public void render(SpriteBatch batch, float delta) {
        for (Map.Entry<Vector2, Texture> entry : textures.entrySet()) {
            batch.draw(entry.getValue(), x + entry.getKey().x, y + entry.getKey().y);
        }
    }

}
