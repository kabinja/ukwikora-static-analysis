package org.ukwikora.staticanalysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ukwikora.staticanalysis.model.StrategyEntity;

import java.util.Optional;

@Repository
public interface StrategyRepository extends JpaRepository<StrategyEntity, Long> {
    Optional<StrategyEntity> findTopByOrderByActivationDesc();
}
