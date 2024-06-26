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
    SINGLE_ROW_LEFT(55),
    SINGLE_ROW_CENTER(56),
    SINGLE_ROW_RIGHT(57),
    SINGLE_COLUMN_TOP(64),
    SINGLE_COLUMN_CENTER(65),
    SINGLE_COLUMN_BOTTOM(66),
    MOVABLE_TOP_LEFT_CORNER(31),
    MOVABLE_TOP_RIGHT_CORNER(33),
    MOVABLE_BOTTOM_LEFT_CORNER(49),
    MOVABLE_BOTTOM_RIGHT_CORNER(51),
    MOVABLE_TOP_EDGE(32),
    MOVABLE_BOTTOM_EDGE(50),
    MOVABLE_LEFT_EDGE(40),
    MOVABLE_RIGHT_EDGE(42),
    MOVABLE_CENTER(41),
    MOVABLE_SINGLE_ROW_LEFT(58),
    MOVABLE_SINGLE_ROW_CENTER(59),
    MOVABLE_SINGLE_ROW_RIGHT(60),
    MOVABLE_SINGLE_COLUMN_TOP(67),
    MOVABLE_SINGLE_COLUMN_CENTER(68),
    MOVABLE_SINGLE_COLUMN_BOTTOM(69);


    private final int textureId;

    PlatformType(int textureId) {
        this.textureId = textureId;
    }
}
