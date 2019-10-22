package com.isobar.wallet.service;

import com.isobar.wallet.entity.Statement;
import com.isobar.wallet.repository.StatementRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatementServiceImpl implements StatementService {

  @Autowired private StatementRepository statementRepository;

  @Override
  public List<Statement> retrieveStatements(String accountId) {
    final LocalDateTime currentDate = LocalDateTime.now();
    final LocalDateTime fromDate = currentDate.minusDays(30);
    final List<Statement> statements =
        statementRepository.findAllByTransactionTimeGreaterThan(fromDate);
    return statements;
  }

  @Override
  public List<Statement> retrieveHistoricalStatement(
      String accountId, LocalDateTime fromDate, LocalDateTime toDate) {
    final List<Statement> statements =
        statementRepository.findAllByTransactionTimeBetween(fromDate, toDate);
    return statements;
  }
}
