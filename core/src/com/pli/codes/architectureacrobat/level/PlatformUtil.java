package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlatformUtil {

    static final Map<Integer, Texture> PLATFORM_TEXTURES = new HashMap<>();

    static {
        for (int i = 1; i < 10; i++) {
            PLATFORM_TEXTURES.put(i, new Texture("platforms/IndustrialTile_0" + i + ".png"));
        }
        for (int i = 10; i <= 81; i++) {
            PLATFORM_TEXTURES.put(i, new Texture("platforms/IndustrialTile_" + i + ".png"));
        }
    }

    public static Map<Vector2, Texture> preparePlatformTextures(List<Rectangle> platforms) {
        Map<Vector2, Texture> textures = new HashMap<>();
        for (Rectangle platform : platforms) {
            for (int x = 0; x < platform.getWidth(); x++) {
                for (int y = 0; y < platform.getHeight(); y++) {
                    float xPos = (platform.x + x) * 32;
                    float yPos = (platform.y + y) * 32;
                    int textureId = selectTexture(x, y, (int) platform.getWidth(), (int) platform.getHeight());
                    textures.put(new Vector2(xPos, yPos), PLATFORM_TEXTURES.get(textureId));
                }
            }
        }
        return textures;
    }

    public static int selectTexture(int x, int y, int platformAmountX, int platformAmountY) {
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

    public static List<Rectangle> prepareScaledPlatforms(List<Rectangle> platforms) {
        List<Rectangle> scaledPlatforms = new ArrayList<>();
        for (Rectangle platform : platforms) {
            scaledPlatforms.add(
                new Rectangle(platform.x * 32, platform.y * 32, platform.width * 32, platform.height * 32));
        }
        return scaledPlatforms;
    }

}
