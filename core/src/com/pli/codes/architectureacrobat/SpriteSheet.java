package com.pli.codes.architectureacrobat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;

@Getter
public enum SpriteSheet {
    PLAYER_ONE("animation-sheet-1.png", 7, 16),
    PLAYER_TWO("animation-sheet-2.png", 7, 10),
    LEVEL_TARGET("Entry.png", 8, 1),
    ;

    private final Texture sheetTexture;
    private final TextureRegion[] frames;

    SpriteSheet(String path, int frameColumns, int frameRows) {
        sheetTexture = new Texture(Gdx.files.internal(path));
        TextureRegion[][] textureRegions = TextureRegion.split(sheetTexture,
            sheetTexture.getWidth() / frameColumns,
            sheetTexture.getHeight() / frameRows);
        frames = new TextureRegion[frameColumns * frameRows];
        int index = 0;
        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameColumns; j++) {
                frames[index++] = textureRegions[i][j];
            }
        }
    }

    public TextureRegion[] getFrames(int startFrame, int endFrame) {
        TextureRegion[] region = new TextureRegion[endFrame - startFrame + 1];
        System.arraycopy(frames, startFrame, region, 0, region.length);
        return region;
    }

}
