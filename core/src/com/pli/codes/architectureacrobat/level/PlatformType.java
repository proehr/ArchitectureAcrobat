package com.pli.codes.architectureacrobat.level;

import lombok.Getter;

@Getter
public enum PlatformType {
    TOP_LEFT_CORNER(4),
    TOP_RIGHT_CORNER(6),
    BOTTOM_LEFT_CORNER(22),
    BOTTOM_RIGHT_CORNER(24),
    TOP_EDGE(5),
    BOTTOM_EDGE(23),
    LEFT_EDGE(13),
    RIGHT_EDGE(15),
    CENTER(14),
    OUTSIDE_CORNER_TOP_LEFT(7),
    OUTSIDE_CORNER_TOP_RIGHT(8),
    OUTSIDE_CORNER_BOTTOM_LEFT(16),
    OUTSIDE_CORNER_BOTTOM_RIGHT(17),
    SINGLE_ROW_LEFT(55),
    SINGLE_ROW_CENTER(56),
    SINGLE_ROW_RIGHT(57),
    SINGLE_COLUMN_TOP(64),
    SINGLE_COLUMN_CENTER(65),
    SINGLE_COLUMN_BOTTOM(66);

    private final int textureId;

    PlatformType(int textureId) {
        this.textureId = textureId;
    }
}
