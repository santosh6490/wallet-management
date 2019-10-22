package com.isobar.wallet.web;

import com.isobar.wallet.dto.StatementDTO;
import com.isobar.wallet.entity.Statement;
import com.isobar.wallet.exception.InvalidRequestException;
import com.isobar.wallet.mapper.Mapper;
import com.isobar.wallet.service.StatementService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatementController {

  private static final Logger LOGGER = LoggerFactory.getLogger(StatementController.class);

  @Autowired private StatementService statementService;

  @Autowired private Mapper mapper;

  @GetMapping("/accounts/{id}/statements/")
  public List<StatementDTO> retrieveStatementForLastThirtyDays(@PathVariable String id) {
    LOGGER.info("Retrieve Statement for account : {} for last 30 days", id);
    final List<Statement> statements = statementService.retrieveStatements(id);
    List<StatementDTO> statementDTOS = new ArrayList<>();
    statements
        .stream()
        .forEach(statement -> statementDTOS.add(mapper.map(statement, new StatementDTO())));
    return statementDTOS;
  }

  @GetMapping("/accounts/{id}/statements/{fromDate}/{toDate}")
  public List<StatementDTO> retrieveStatementForGivenDateRange(
      @PathVariable String id,
      @PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate fromDate,
      @PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate toDate) {
    LOGGER.info("Retrieve Statement for account : {} from {} to {}", id, fromDate, toDate);
    final LocalDateTime fromDateTime = LocalDateTime.of(fromDate, LocalTime.MIN);
    final LocalDateTime toDateTime = LocalDateTime.of(toDate, LocalTime.MIN);
    validateDateRange(fromDateTime, toDateTime);
    final List<Statement> statements =
        statementService.retrieveHistoricalStatement(id, fromDateTime, toDateTime);
    List<StatementDTO> statementDTOS = new ArrayList<>();
    statements
        .stream()
        .forEach(statement -> statementDTOS.add(mapper.map(statement, new StatementDTO())));
    return statementDTOS;
  }

  private void validateDateRange(final LocalDateTime fromDate, final LocalDateTime toDate) {
    LOGGER.info("Validating date range from {} to {}", fromDate, toDate);
    if (fromDate == null || toDate == null || fromDate.isAfter(toDate)) {
      throw new InvalidRequestException("Invalid date range");
    }
  }
}
