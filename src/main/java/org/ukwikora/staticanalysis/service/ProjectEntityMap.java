package org.ukwikora.staticanalysis.service;

import org.ukwikora.model.Project;
import org.ukwikora.model.Statement;
import org.ukwikora.staticanalysis.model.ProjectEntity;
import org.ukwikora.staticanalysis.model.ProjectVersionEntity;
import org.ukwikora.staticanalysis.model.StatementVersionEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProjectEntityMap {
    private final Map<Project, ProjectEntity> projectEntityMap;
    private final Map<Project, ProjectVersionEntity> projectVersionEntityMap;
    private final Map<Statement, StatementVersionEntity> statementVersionEntityMap;

    public ProjectEntityMap(){
        this.projectEntityMap = new HashMap<>();
        this.projectVersionEntityMap = new HashMap<>();
        this.statementVersionEntityMap = new HashMap<>();
    }

    public ProjectEntity getProjectEntity(final Project project) {
        return this.projectEntityMap.get(project);
    }

    public void setProjectEntity(final Project project, final ProjectEntity projectEntity) {
        this.projectEntityMap.put(project, projectEntity);
    }

    public ProjectVersionEntity getProjectVersionEntity(final Project project) {
        return this.projectVersionEntityMap.get(project);
    }

    public void setProjectVersionEntity(final Project project, final ProjectVersionEntity projectVersionEntity){
        this.projectVersionEntityMap.put(project, projectVersionEntity);
    }

    public StatementVersionEntity getStatementVersionEntity(final Statement statement){
        return statementVersionEntityMap.get(statement);
    }

    public void setStatementVersionEntity(Statement statement, StatementVersionEntity statementVersionEntity) {
        this.statementVersionEntityMap.put(statement, statementVersionEntity);
    }

    public Set<Project> getProjects(){
        return this.projectVersionEntityMap.keySet();
    }
}
