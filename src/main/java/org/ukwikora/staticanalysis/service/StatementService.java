package org.ukwikora.staticanalysis.service;

import org.ukwikora.model.Statement;
import org.ukwikora.staticanalysis.model.ProjectVersionEntity;
import org.ukwikora.staticanalysis.model.StatementEntity;
import org.ukwikora.staticanalysis.model.StatementVersionEntity;

public interface StatementService {
    StatementEntity getOrCreate(Statement statement);
    StatementVersionEntity saveVersion(ProjectVersionEntity projectVersionEntity, Statement statement);
}
