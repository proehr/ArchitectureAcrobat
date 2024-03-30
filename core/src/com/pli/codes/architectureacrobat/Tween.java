package com.pli.codes.architectureacrobat;

import com.cyphercove.gdxtween.TweenRunner;

/**
 * Singleton class to access the tween library.
 */
public class Tween {

    private static TweenRunner INSTANCE;

    private Tween() {
    }

    public static TweenRunner getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TweenRunner();
        }
        return INSTANCE;
    }

}
