package com.isobar.wallet.service;

import com.isobar.wallet.entity.Statement;
import java.time.LocalDateTime;
import java.util.List;

public interface StatementService {

  List<Statement> retrieveStatements(String accountId);

  List<Statement> retrieveHistoricalStatement(
      String accountId, LocalDateTime fromDate, LocalDateTime toDate);
}
