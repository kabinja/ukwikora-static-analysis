package org.ukwikora.staticanalysis.service.impl;

import org.springframework.stereotype.Service;
import org.ukwikora.model.*;
import org.ukwikora.staticanalysis.model.ProjectEntity;
import org.ukwikora.staticanalysis.model.ProjectVersionEntity;
import org.ukwikora.staticanalysis.model.StatementVersionEntity;
import org.ukwikora.staticanalysis.repository.ProjectRepository;
import org.ukwikora.staticanalysis.repository.ProjectVersionRepository;
import org.ukwikora.staticanalysis.service.ProjectEntityMap;
import org.ukwikora.staticanalysis.service.ProjectService;
import org.ukwikora.staticanalysis.service.StatementService;
import org.ukwikora.utils.StringUtils;

import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectVersionRepository projectVersionRepository;
    private final StatementService statementService;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              ProjectVersionRepository projectVersionRepository,
                              StatementService statementService) {
        this.projectRepository = projectRepository;
        this.projectVersionRepository = projectVersionRepository;
        this.statementService = statementService;
    }

    @Override
    public ProjectEntityMap saveProjects(Set<Project> projects) throws Exception {
        ProjectEntityMap entityMap = new ProjectEntityMap();

        for(Project project: projects){
            getProjectEntity(project, entityMap);
            getProjectVersionEntity(project, entityMap);
            getStatements(project, entityMap);
        }

        setDependencies(entityMap);

        return entityMap;
    }

    private void getStatements(Project project, ProjectEntityMap entityMap) {
        ProjectVersionEntity projectVersionEntity = entityMap.getProjectVersionEntity(project);

        for(Statement statement: getStatements(project)){
            final StatementVersionEntity statementVersionEntity = statementService.saveVersion(projectVersionEntity, statement);
            entityMap.setStatementVersionEntity(statement, statementVersionEntity);
        }
    }

    private void getProjectVersionEntity(Project project, ProjectEntityMap entityMap) {
        final Optional<ProjectVersionEntity> version = projectVersionRepository.findByCommitId(project.getCommitId());

        if(version.isPresent()){
            entityMap.setProjectVersionEntity(project, version.get());
        }
        else{
            ProjectVersionEntity projectVersionEntity = new ProjectVersionEntity();

            projectVersionEntity.setProjectEntity(entityMap.getProjectEntity(project));
            projectVersionEntity.setCommitId(project.getCommitId());
            projectVersionEntity.setCommitDate(project.getDate());

            final ProjectVersionEntity save = projectVersionRepository.save(projectVersionEntity);
            entityMap.setProjectVersionEntity(project, save);
        }
    }

    private Set<Statement> getStatements(Project project){
        HashSet<Statement> statements = new HashSet<>();

        statements.addAll(project.getStatements(TestCase.class));
        statements.addAll(project.getStatements(UserKeyword.class));
        statements.addAll(project.getStatements(Variable.class));

        return statements;
    }

    private void getProjectEntity(Project project, ProjectEntityMap entityMap) throws Exception {
        final Optional<ProjectEntity> found = projectRepository.findFirstByUrlAndName(
                project.getGitUrl(),
                project.getName());

        if(found.isPresent()){
            entityMap.setProjectEntity(project, found.get());
        }
        else{
            ProjectEntity projectEntity = new ProjectEntity();

            projectEntity.setName(project.getName());
            projectEntity.setSlug(StringUtils.toBeautifulUrl(project.getName(), ""));
            projectEntity.setUrl(project.getGitUrl());

            entityMap.setProjectEntity(project, projectRepository.save(projectEntity));
        }
    }

    private void setDependencies(ProjectEntityMap entityMap) {
        for(Project child: entityMap.getProjects()){
            final ProjectVersionEntity childEntity = entityMap.getProjectVersionEntity(child);

            for(Project parent: child.getDependencies()){
                childEntity.addDependency(entityMap.getProjectVersionEntity(parent));
            }
        }
    }
}
