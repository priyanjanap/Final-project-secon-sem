package org.example._backend.util;

import java.util.Base64;

public class PorfilePicEncoder {
    public static String profilePicToBase64(byte[] profilePic) {
        return Base64.getEncoder().encodeToString(profilePic);
    }
}
