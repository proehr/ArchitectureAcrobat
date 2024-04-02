package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Map;

public class MovablePlatform extends Platform {

    public MovablePlatform(Rectangle rectangle) {
        super(rectangle);
    }

    @Override
    protected Map<Vector2, Texture> prepareTextures(float width, float height) {
        return PlatformUtil.prepareMovablePlatformTextures(width, height);
    }
}
