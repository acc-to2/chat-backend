package com.totwo.chat.common;

public record CustomError(
        String code,
        String message
) {
}
