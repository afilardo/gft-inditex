package com.gft.inditex.infrastructure.rest;

import com.gft.inditex.domain.common.exceptions.DomainError;
import com.gft.inditex.domain.common.exceptions.DomainErrorCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

  private final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(DomainError.class)
  @ResponseBody
  public ErrorResponse domainError(DomainError error) {
    return ErrorResponse.builder(error, parse(error.domainErrorCode()), error.getMessage()).build();
  }

  @ExceptionHandler(Exception.class)
  public ErrorResponse generalException(Exception ex) {
    logger.error(ex.getMessage(), ex);
    return ErrorResponse.builder(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()).build();
  }

  private static HttpStatus parse(DomainErrorCode errorCode) {
    return switch (errorCode) {
      case INVALID -> HttpStatus.BAD_REQUEST;
      case NOT_FOUND -> HttpStatus.NOT_FOUND;
      case DUPLICATED -> HttpStatus.CONFLICT;
      default -> HttpStatus.INTERNAL_SERVER_ERROR;
    };
  }
}
