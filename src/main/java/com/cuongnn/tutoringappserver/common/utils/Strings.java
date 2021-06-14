package com.cuongnn.tutoringappserver.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Strings {

    private static final String EMPTY = "";

    public static String refactor(String inVal) {
        return Optional.ofNullable(inVal).map(String::trim).orElse(EMPTY);
    }

    public static String nvl(String inVal) {
        return Optional.ofNullable(inVal).orElse(EMPTY);
    }
}
