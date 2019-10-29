package org.ukwikora.staticanalysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ukwikora.staticanalysis.model.StatementEntity;

import java.util.Optional;

public interface StatementRepository extends JpaRepository<StatementEntity, Long> {
    Optional<StatementEntity> findFirstByTypeAndNameAndFileAndStartLineAndEndLine(
            String type,
            String name,
            String file,
            int startLine,
            int endLine
    );
}
