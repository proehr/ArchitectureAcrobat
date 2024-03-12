package com.pli.codes.architectureacrobat;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;

@Getter
public enum AnimationData {
    IDLE(SpriteSheet.PLAYER_ONE, 0, 3),
    CROUCH(SpriteSheet.PLAYER_ONE, 4, 7),
    RUN(SpriteSheet.PLAYER_ONE, 8, 13),
    JUMP(SpriteSheet.PLAYER_ONE, 14, 17),
    FALL(SpriteSheet.PLAYER_ONE, 22, 23),
    ;

    public static final float FRAME_DURATION = 0.25f;
    private final Animation<TextureRegion> animation;

    AnimationData(SpriteSheet spriteSheet, int startFrame, int endFrame) {
        animation = new Animation<>(FRAME_DURATION, spriteSheet.getFrames(startFrame, endFrame));
    }
}
