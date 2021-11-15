package com.self.unicorn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "not a valid request")
public class UnicornValidationException extends UnicornException {

  public UnicornValidationException(String cause) {
    super(cause);
  }
}
