package org.ukwikora.staticanalysis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ukwikora.model.Statement;
import org.ukwikora.staticanalysis.model.ProjectVersionEntity;
import org.ukwikora.staticanalysis.model.StatementEntity;
import org.ukwikora.staticanalysis.model.StatementVersionEntity;
import org.ukwikora.staticanalysis.repository.StatementRepository;
import org.ukwikora.staticanalysis.repository.StatementVersionRepository;
import org.ukwikora.staticanalysis.service.StatementService;

import java.util.*;

@Service
public class StatementServiceImpl implements StatementService {
    private final StatementRepository repository;
    private final StatementVersionRepository versionRepository;

    @Autowired
    public StatementServiceImpl(StatementRepository repository, StatementVersionRepository versionRepository) {
        this.repository = repository;
        this.versionRepository = versionRepository;
    }

    @Override
    public StatementEntity getOrCreate(Statement statement) {
            final Optional<StatementEntity> entity = repository.findFirstByTypeAndNameAndFileAndStartLineAndEndLine(
                    statement.getClass().getSimpleName(),
                    statement.getName(),
                    statement.getFileName(),
                    statement.getLineRange().getStart(),
                    statement.getLineRange().getEnd());

        return entity.orElseGet(() -> createEntity(statement));
    }

    @Override
    public StatementVersionEntity saveVersion(ProjectVersionEntity projectVersionEntity, Statement statement) {
        StatementEntity statementEntity = getOrCreate(statement);

        StatementVersionEntity statementVersionEntity = new StatementVersionEntity();

        statementVersionEntity.setProject(projectVersionEntity);
        statementVersionEntity.setStatementEntity(statementEntity);
        statementVersionEntity.setDeadCode(statement.isDeadCode());

        return versionRepository.save(statementVersionEntity);
    }

    private StatementEntity createEntity(Statement statement) {
        StatementEntity entity = new StatementEntity();

        entity.setName(statement.getName());
        entity.setFile(statement.getFileName());
        entity.setStartLine(statement.getLineRange().getStart());
        entity.setEndLine(statement.getLineRange().getEnd());
        entity.setType(statement.getClass().getSimpleName());

        return repository.save(entity);
    }
}
