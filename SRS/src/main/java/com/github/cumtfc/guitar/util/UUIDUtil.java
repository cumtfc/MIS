package com.github.cumtfc.guitar.util;

import java.util.UUID;

public class UUIDUtil {

    /**
     * uuid
     *
     * @return string
     * @author mahui
     */
    public static String generate() {
        String uuidString = UUID.randomUUID().toString();
        return uuidString.replace("-", "");
    }
}
