package com.supplyhouse.exception;

public class PreconditionFailedException extends RuntimeException {
  private final String code;

  public PreconditionFailedException(String code, String message) {
    super(message);
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
