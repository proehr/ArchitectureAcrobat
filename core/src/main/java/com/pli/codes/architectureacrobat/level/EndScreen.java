package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndScreen {

    private final BitmapFont font;
    private final GlyphLayout congratsText;
    private final GlyphLayout quitText;

    EndScreen() {
        font = new BitmapFont();
        font.getData().setScale(2);
        congratsText = new GlyphLayout(font, "Congratulations, you have completed the game!");
        quitText = new GlyphLayout(font, "Quit");
    }

    public void render(SpriteBatch batch) {
        font.draw(
            batch,
            "Congratulations, you have completed the game!",
            (1920 - congratsText.width) / 2f,
            (1080 - congratsText.height) / 2f
        );
        font.draw(
            batch,
            "Quit",
            (1920 - quitText.width) / 2f,
            176
        );
    }
}
