package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.math.Rectangle;
import com.pli.codes.architectureacrobat.interaction.InteractableData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LevelInitData {

    private float playerStartX;
    private float playerStartY;
    private Rectangle[] platforms;
    private InteractableData target;
    private InteractableData[] interactables;

}
