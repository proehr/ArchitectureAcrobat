package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Map;
import lombok.Getter;

public class Platform {

    protected transient Map<Vector2, Texture> textures;

    @Getter
    private final Rectangle bounds;

    public Platform(Rectangle rectangle) {
        this.bounds = new Rectangle(rectangle.x * 32, rectangle.y * 32, rectangle.width * 32, rectangle.height * 32);
        textures = prepareTextures(rectangle.width, rectangle.height);
    }

    protected Map<Vector2, Texture> prepareTextures(float width, float height) {
        return PlatformUtil.preparePlatformTextures(width, height);
    }

    public void render(SpriteBatch batch, float delta) {
        for (Map.Entry<Vector2, Texture> entry : textures.entrySet()) {
            batch.draw(entry.getValue(), bounds.x + entry.getKey().x, bounds.y + entry.getKey().y);
        }
    }

}
