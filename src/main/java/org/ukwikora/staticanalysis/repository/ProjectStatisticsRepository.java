package org.ukwikora.staticanalysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ukwikora.staticanalysis.model.ProjectStatisticsEntity;
import org.ukwikora.staticanalysis.model.ProjectVersionEntity;

import java.util.Optional;

public interface ProjectStatisticsRepository extends JpaRepository<ProjectStatisticsEntity, Long> {
    Optional<ProjectStatisticsEntity> findByProjectVersionEntity(ProjectVersionEntity projectVersionEntity);
}
