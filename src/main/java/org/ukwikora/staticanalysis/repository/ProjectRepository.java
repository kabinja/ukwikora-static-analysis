package org.ukwikora.staticanalysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ukwikora.staticanalysis.model.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
