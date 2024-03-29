package com.pli.codes.architectureacrobat.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;

@Getter
public enum AnimationData {
    IDLE(SpriteSheet.PLAYER_ONE, 0, 3, 0.3f),
    CROUCH(SpriteSheet.PLAYER_ONE, 4, 7, 0.25f),
    RUN(SpriteSheet.PLAYER_ONE, 8, 13, 0.2f),
    JUMP(SpriteSheet.PLAYER_ONE, 16, 17, 0.2f),
    FALL(SpriteSheet.PLAYER_ONE, 22, 23, 0.2f),
    TARGET_OPEN(SpriteSheet.LEVEL_TARGET, 0, 4, 0.3f),
    ;

    private final Animation<TextureRegion> animation;

    AnimationData(SpriteSheet spriteSheet, int startFrame, int endFrame, float frameDuration) {
        animation = new Animation<>(frameDuration, spriteSheet.getFrames(startFrame, endFrame));
    }
}
