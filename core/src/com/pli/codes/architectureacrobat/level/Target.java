package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.pli.codes.architectureacrobat.AnimationData;
import com.pli.codes.architectureacrobat.interaction.Interactable;
import java.beans.PropertyChangeEvent;
import lombok.Getter;

@Getter
public class Target extends Interactable {

    private static final Texture TARGET_TOP_LEFT_PLATFORM = new Texture("platforms/IndustrialTile_58.png");
    private static final Texture TARGET_TOP_RIGHT_PLATFORM = new Texture("platforms/IndustrialTile_60.png");

    private final Rectangle bounds;

    private boolean targetReached;
    private float stateTime = 0;

    public Target(Rectangle bounds) {
        this.bounds = new Rectangle(bounds.x * 32, bounds.y * 32, bounds.width * 32, bounds.height * 32);
    }

    public void render(SpriteBatch batch) {
        if (targetReached) {
            stateTime += Gdx.graphics.getDeltaTime();
        }
        TextureRegion currentFrame = AnimationData.TARGET_OPEN.getAnimation().getKeyFrame(stateTime, false);
        batch.draw(currentFrame, bounds.x, bounds.y);
        batch.draw(currentFrame, (bounds.x + 32), bounds.y);
        batch.draw(TARGET_TOP_LEFT_PLATFORM, bounds.x, (bounds.y + 64));
        batch.draw(TARGET_TOP_RIGHT_PLATFORM, (bounds.x + 32), (bounds.y + 64));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof Rectangle) {
            Rectangle playerBounds = (Rectangle) evt.getNewValue();
            if (bounds.overlaps(playerBounds)) {
                targetReached = true;
            }
        }
    }
}
