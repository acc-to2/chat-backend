package com.totwo.chat.service.util;

public class PrefixUtil {

    public static final String USER_PREFIX = "USER#";
    public static final String ROOM_PREFIX = "ROOM#";
    public static final String MSG_PREFIX = "MSG#";

    // 붙이기
    public static String withUserPrefix(String value) {
        return USER_PREFIX + value;
    }

    public static String withRoomPrefix(String value) {
        return ROOM_PREFIX + value;
    }

    public static String withMsgPrefix(String value) {
        return MSG_PREFIX + value;
    }

    // 떼기
    public static String removeUserPrefix(String value) {
        return removePrefix(value, USER_PREFIX);
    }

    public static String removeRoomPrefix(String value) {
        return removePrefix(value, ROOM_PREFIX);
    }

    public static String removeMsgPrefix(String value) {
        return removePrefix(value, MSG_PREFIX);
    }

    private static String removePrefix(String value, String prefix) {
        if (value != null && value.startsWith(prefix)) {
            return value.substring(prefix.length());
        }
        return value;
    }

    // 유틸 클래스이므로 생성자 private
    private PrefixUtil() {}
}
