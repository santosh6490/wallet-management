package com.isobar.wallet.exception;

public class FundProcessingFailureException extends RuntimeException {

  public FundProcessingFailureException(String message) {
    super(message);
  }

  public FundProcessingFailureException(String message, Throwable cause) {
    super(message, cause);
  }

  public FundProcessingFailureException(Throwable cause) {
    super(cause);
  }

  public FundProcessingFailureException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
