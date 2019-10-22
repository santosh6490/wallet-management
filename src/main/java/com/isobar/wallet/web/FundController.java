package com.isobar.wallet.web;

import com.isobar.wallet.exception.FundProcessingFailureException;
import com.isobar.wallet.service.FundService;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FundController {

  private static final Logger LOGGER = LoggerFactory.getLogger(FundController.class);

  @Autowired private FundService fundService;

  @PostMapping("/accounts/{id}/funds/{amount}")
  public ResponseEntity<Boolean> credit(@PathVariable Long id, @PathVariable BigDecimal amount) {
    LOGGER.info("adding amount {} to account : {}", amount, id);
    final Boolean isCredited = fundService.credit(id, amount);
    if (isCredited) {
      LOGGER.info("amount {} credited to account : {}", amount, id);
      return new ResponseEntity<>(isCredited, HttpStatus.OK);
    } else {
      LOGGER.error("Failed to add amount to account accountId : {}", id);
      throw new FundProcessingFailureException("Wrong fund request");
    }
  }

  @DeleteMapping("/accounts/{id}/funds/{amount}")
  public ResponseEntity<Boolean> debit(@PathVariable Long id, @PathVariable BigDecimal amount) {
    LOGGER.info("debiting amount {} to account : {}", amount, id);
    final Boolean isDebited = fundService.debit(id, amount);
    if (isDebited) {
      LOGGER.info("amount {} debited from account : {}", amount, id);
      return new ResponseEntity<>(isDebited, HttpStatus.OK);
    } else {
      LOGGER.error("Failed to deduct amount {} to account accountId : {}", amount, id);
      throw new FundProcessingFailureException("Wrong fund request");
    }
  }
}
