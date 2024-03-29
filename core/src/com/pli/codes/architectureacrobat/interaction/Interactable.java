package com.pli.codes.architectureacrobat.interaction;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.pli.codes.architectureacrobat.level.Target;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({
    @Type(value = Target.class, name = "target")
})
public abstract class Interactable {

    public abstract void render(SpriteBatch batch, float delta);

    public abstract boolean detectInteraction(Object newValue);

    public abstract void handleInteraction();
}
