package com.zemoso.zinteract.exception;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Builder
@NoArgsConstructor
public class DataNotFoundException extends Throwable{

  private static final Logger log = LoggerFactory.getLogger(DataNotFoundException.class);
  public DataNotFoundException(String message, Throwable cause) {
    log.error(message);
  }

  public DataNotFoundException(String message) {
    log.error(message);
  }
}
