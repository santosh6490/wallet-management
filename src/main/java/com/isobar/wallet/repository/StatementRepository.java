package com.isobar.wallet.repository;

import com.isobar.wallet.entity.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface StatementRepository extends CrudRepository<Statement, Long> {

  List<Statement> findAllByTransactionTimeGreaterThan(LocalDateTime fromDate);

  List<Statement> findAllByTransactionTimeBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
