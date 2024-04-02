package com.pli.codes.architectureacrobat.interaction;

import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ButtonData extends InteractableData {

    private Rectangle platform;
    private float endPosX;
    private float endPosY;
}
