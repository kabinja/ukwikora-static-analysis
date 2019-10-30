package org.ukwikora.staticanalysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ukwikora.staticanalysis.model.CloneClusterEntity;

public interface CloneClusterRepository extends JpaRepository<CloneClusterEntity, Long> {
}
