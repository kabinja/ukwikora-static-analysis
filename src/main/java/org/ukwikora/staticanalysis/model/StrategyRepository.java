package org.ukwikora.staticanalysis.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategyRepository extends JpaRepository<Strategy, Long> {
    Strategy findTopByOrderByActivationDesc();
}
