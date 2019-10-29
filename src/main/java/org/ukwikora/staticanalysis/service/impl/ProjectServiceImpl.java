package org.ukwikora.staticanalysis.service.impl;

import org.springframework.stereotype.Service;
import org.ukwikora.model.*;
import org.ukwikora.staticanalysis.model.ProjectEntity;
import org.ukwikora.staticanalysis.model.ProjectVersionEntity;
import org.ukwikora.staticanalysis.model.StatementVersionEntity;
import org.ukwikora.staticanalysis.repository.ProjectRepository;
import org.ukwikora.staticanalysis.repository.ProjectVersionRepository;
import org.ukwikora.staticanalysis.service.ProjectService;
import org.ukwikora.staticanalysis.service.StatementService;
import org.ukwikora.utils.StringUtils;

import javax.transaction.Transactional;
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
    @Transactional
    public void saveProjects(Set<Project> projects) throws Exception {
        final Map<Project, ProjectEntity> projectEntityMap = getProjectEntities(projects);

        for(Project project: projects){
            if(projectVersionRepository.findByCommitId(project.getCommitId()).isPresent()){
                continue;
            }

            final ProjectVersionEntity projectVersionEntity = createProjectVersionEntity(project, projectEntityMap.get(project));
            final Map<Statement, StatementVersionEntity> statementEntityMap = getStatements(project, projectVersionEntity);
        }
    }

    private Map<Statement, StatementVersionEntity> getStatements(Project project, ProjectVersionEntity projectVersionEntity) {
        Set<Statement> statements = getStatements(project);
        Map<Statement, StatementVersionEntity> statementMap = new HashMap<>(statements.size());

        for(Statement statement: statements){
            final StatementVersionEntity statementVersionEntity = statementService.saveVersion(project, projectVersionEntity, statement);
            statementMap.put(statement, statementVersionEntity);
        }

        return statementMap;
    }

    private ProjectVersionEntity createProjectVersionEntity(Project project, ProjectEntity projectEntity) {
        ProjectVersionEntity projectVersionEntity = new ProjectVersionEntity();

        projectVersionEntity.setProjectEntity(projectEntity);
        projectVersionEntity.setCommitId(project.getCommitId());
        projectVersionEntity.setCommitDate(project.getDate());
        projectVersionEntity.setLinesOfCode(project.getLoc());
        projectVersionEntity.setDeadCode(project.getDeadLoc());

        return projectVersionRepository.save(projectVersionEntity);
    }

    private Set<Statement> getStatements(Project project){
        HashSet<Statement> statements = new HashSet<>();

        statements.addAll(project.getStatements(TestCase.class));
        statements.addAll(project.getStatements(UserKeyword.class));
        statements.addAll(project.getStatements(Variable.class));

        return statements;
    }

    private Map<Project, ProjectEntity> getProjectEntities(Set<Project> projects) throws Exception {
        Map<Project, ProjectEntity> projectEntityMap = new HashMap<>(projects.size());

        for(Project project: projects){
            final Optional<ProjectEntity> found = projectRepository.findFirstByUrlAndName(
                    project.getGitUrl(),
                    project.getName());

            if(found.isPresent()){
                projectEntityMap.put(project, found.get());
            }
            else{
                ProjectEntity projectEntity = new ProjectEntity();

                projectEntity.setName(project.getName());
                projectEntity.setSlug(StringUtils.toBeautifulUrl(project.getName(), ""));
                projectEntity.setUrl(project.getGitUrl());

                projectEntityMap.put(project, projectRepository.save(projectEntity));
            }
        }

        return projectEntityMap;
    }
}
