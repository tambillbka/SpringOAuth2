package com.education.tutoringappserver.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Strings {

    private static final String EMPTY = "";

    public static String refactor(String inVal) {
        return Optional.ofNullable(inVal).map(Strings::trimAllWhiteSpace).orElse(EMPTY);
    }

    public static String nvl(String inVal) {
        return Optional.ofNullable(inVal).orElse(EMPTY);
    }

    public static String trimAllWhiteSpace(String inVal) {
        inVal = StringUtils.trimLeadingWhitespace(nvl(inVal));
        return StringUtils.trimTrailingWhitespace(nvl(inVal));
    }
}
