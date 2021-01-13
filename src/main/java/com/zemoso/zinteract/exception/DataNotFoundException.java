package com.zemoso.zinteract.exception;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@NoArgsConstructor
@Slf4j
public class DataNotFoundException extends Throwable {

    public DataNotFoundException(String message, Throwable cause) {
        log.error(message);
    }

    public DataNotFoundException(String message) {
        log.error(message);
    }
}
