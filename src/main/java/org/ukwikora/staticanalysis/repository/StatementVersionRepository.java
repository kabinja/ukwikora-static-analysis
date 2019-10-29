package org.ukwikora.staticanalysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ukwikora.staticanalysis.model.StatementVersionEntity;

public interface StatementVersionRepository extends JpaRepository<StatementVersionEntity, Long> {
}
