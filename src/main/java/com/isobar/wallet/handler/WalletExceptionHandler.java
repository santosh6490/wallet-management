package com.isobar.wallet.handler;

import com.isobar.wallet.exception.AccountNotFoundException;
import com.isobar.wallet.exception.FundProcessingFailureException;
import com.isobar.wallet.exception.InsufficientBalanceException;
import com.isobar.wallet.exception.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WalletExceptionHandler{

  @ExceptionHandler(AccountNotFoundException.class)
  public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException ex){
    return new ResponseEntity("Invalid Account", HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InsufficientBalanceException.class)
  public ResponseEntity<String> handleInsufficientBalanceException(InsufficientBalanceException ex){
    return new ResponseEntity("Insufficient Balance", HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(FundProcessingFailureException.class)
  public ResponseEntity<String> handleFundProcessingFailureException(FundProcessingFailureException ex){
    return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<String> handleInvalidRequestException(InvalidRequestException ex){
    return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception ex){
    return new ResponseEntity("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
