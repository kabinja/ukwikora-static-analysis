package org.ukwikora.staticanalysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ukwikora.staticanalysis.model.ProjectVersionEntity;

import java.util.Optional;

public interface ProjectVersionRepository extends JpaRepository<ProjectVersionEntity, Long> {
    Optional<ProjectVersionEntity> findByCommitId(String commitId);
}
