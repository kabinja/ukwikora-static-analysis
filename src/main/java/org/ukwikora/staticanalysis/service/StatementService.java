package org.ukwikora.staticanalysis.service;

import org.ukwikora.model.Project;
import org.ukwikora.model.Statement;
import org.ukwikora.staticanalysis.model.ProjectVersionEntity;
import org.ukwikora.staticanalysis.model.StatementEntity;
import org.ukwikora.staticanalysis.model.StatementVersionEntity;

public interface StatementService {
    StatementEntity getOrCreate(Statement statement);
    StatementVersionEntity saveVersion(Project project, ProjectVersionEntity projectVersionEntity, Statement statement);
}
