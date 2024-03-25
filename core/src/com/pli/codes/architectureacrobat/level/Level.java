package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

public class Level {

    private static final Map<Integer, Texture> PLATFORM_TEXTURES = new HashMap<>();

    static {
        for (int i = 1; i < 10; i++) {
            PLATFORM_TEXTURES.put(i, new Texture("platforms/IndustrialTile_0" + i + ".png"));
        }
        for (int i = 10; i <= 81; i++) {
            PLATFORM_TEXTURES.put(i, new Texture("platforms/IndustrialTile_" + i + ".png"));
        }
    }

    @Getter
    private final List<Rectangle> scaledPlatforms = new ArrayList<>();
    private final Map<Vector2, Texture> platformTextures = new HashMap<>();
    @Getter
    private final LevelData levelData;

    public Level(LevelData levelData) {
        this.levelData = levelData;
        preparePlatformTextures(levelData.getPlatforms());
        prepareScaledPlatforms(levelData.getPlatforms());
    }

    private void prepareScaledPlatforms(List<Rectangle> platforms) {
        for (Rectangle platform : platforms) {
            scaledPlatforms.add(
                new Rectangle(platform.x * 32, platform.y * 32, platform.width * 32, platform.height * 32));
        }
    }

    private void preparePlatformTextures(List<Rectangle> platforms) {
        for (Rectangle platform : platforms) {
            for (int x = 0; x < platform.getWidth(); x++) {
                for (int y = 0; y < platform.getHeight(); y++) {
                    float xPos = (platform.x + x) * 32;
                    float yPos = (platform.y + y) * 32;
                    int textureId = selectTexture(x, y, (int) platform.getWidth(), (int) platform.getHeight());
                    platformTextures.put(new Vector2(xPos, yPos), PLATFORM_TEXTURES.get(textureId));
                }
            }
        }
    }

    public void render(SpriteBatch batch) {
        renderPlatforms(batch);
        levelData.getTarget().render(batch);
    }

    private void renderPlatforms(SpriteBatch batch) {
        for (Map.Entry<Vector2, Texture> entry : platformTextures.entrySet()) {
            batch.draw(entry.getValue(), entry.getKey().x, entry.getKey().y);
        }
    }

    private int selectTexture(int x, int y, int platformAmountX, int platformAmountY) {
        if (platformAmountX == 1) {
            if (y == 0) {
                return PlatformType.SINGLE_COLUMN_BOTTOM.getTextureId();
            }
            if (y == platformAmountY - 1) {
                return PlatformType.SINGLE_COLUMN_TOP.getTextureId();
            }
            return PlatformType.SINGLE_COLUMN_CENTER.getTextureId();
        }
        if (platformAmountY == 1) {
            if (x == 0) {
                return PlatformType.SINGLE_ROW_LEFT.getTextureId();
            }
            if (x == platformAmountX - 1) {
                return PlatformType.SINGLE_ROW_RIGHT.getTextureId();
            }
            return PlatformType.SINGLE_ROW_CENTER.getTextureId();
        }
        if (x == 0 && y == 0) {
            return PlatformType.BOTTOM_LEFT_CORNER.getTextureId();
        }
        if (x == platformAmountX - 1 && y == 0) {
            return PlatformType.BOTTOM_RIGHT_CORNER.getTextureId();
        }
        if (x == 0 && y == platformAmountY - 1) {
            return PlatformType.TOP_LEFT_CORNER.getTextureId();
        }
        if (x == platformAmountX - 1 && y == platformAmountY - 1) {
            return PlatformType.TOP_RIGHT_CORNER.getTextureId();
        }
        if (x == 0) {
            return PlatformType.LEFT_EDGE.getTextureId();
        }
        if (x == platformAmountX - 1) {
            return PlatformType.RIGHT_EDGE.getTextureId();
        }
        if (y == 0) {
            return PlatformType.BOTTOM_EDGE.getTextureId();
        }
        if (y == platformAmountY - 1) {
            return PlatformType.TOP_EDGE.getTextureId();
        }
        return PlatformType.CENTER.getTextureId();

    }

}
