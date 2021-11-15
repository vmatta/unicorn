package com.self.unicorn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "entity doesn't exist")
public class UnicornNotFoundException extends UnicornException {

  public UnicornNotFoundException(String cause) {
    super(cause);
  }
}
