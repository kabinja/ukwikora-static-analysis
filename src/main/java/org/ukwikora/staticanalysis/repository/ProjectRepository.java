package org.ukwikora.staticanalysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ukwikora.staticanalysis.model.ProjectEntity;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    Optional<ProjectEntity> findFirstByUrlAndName(String url, String name);
}
