package com.pli.codes.architectureacrobat.interaction;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.pli.codes.architectureacrobat.level.Platform;
import java.util.Collections;
import java.util.List;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({
    @Type(value = Target.class, name = "target"),
    @Type(value = Button.class, name = "button")
})
public interface Interactable {

    default void update(float delta) {
    }

    void render(SpriteBatch batch, float delta);

    boolean detectInteraction(Object newValue);

    void handleInteraction(Object newValue);

    default List<Platform> getPlatforms() {
        return Collections.emptyList();
    }
}
