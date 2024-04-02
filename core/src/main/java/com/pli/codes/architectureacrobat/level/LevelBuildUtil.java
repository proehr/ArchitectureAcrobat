package com.pli.codes.architectureacrobat.level;

import com.badlogic.gdx.math.Rectangle;
import com.pli.codes.architectureacrobat.interaction.Button;
import com.pli.codes.architectureacrobat.interaction.ButtonData;
import com.pli.codes.architectureacrobat.interaction.Interactable;
import com.pli.codes.architectureacrobat.interaction.InteractableData;
import com.pli.codes.architectureacrobat.interaction.Target;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LevelBuildUtil {

    public static List<Platform> buildPlatforms(Rectangle[] platforms) {
        List<Platform> platformList = new ArrayList<Platform>();
        for (Rectangle platform : platforms) {
            platformList.add(new Platform(platform));
        }
        return platformList;
    }

    public static Target buildTarget(InteractableData target) {
        return new Target(target);
    }

    public static List<Interactable> buildInteractables(InteractableData[] interactables) {
        List<Interactable> interactableList = new ArrayList<Interactable>();
        for (InteractableData interactable : interactables) {
            if (interactable instanceof ButtonData) {
                interactableList.add(new Button((ButtonData) interactable));
            } else {
                interactableList.add(new Target(interactable));
            }
        }
        return interactableList;
    }
}
