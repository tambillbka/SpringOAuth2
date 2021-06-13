package com.cuongnn.tutoringappserver.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Strings {

    private static final String EMPTY = "";

    public static String refactor(String inVal) {
        return (inVal == null || inVal.length() == 0)
                ? EMPTY : inVal.trim();
    }
}
