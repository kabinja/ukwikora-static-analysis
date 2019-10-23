package org.ukwikora.staticanalysis.model;

import javax.persistence.*;

@Entity
@Table(name = "statements_version")
public class StatementVersionEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @ManyToOne
    private ProjectVersionEntity project;
    @ManyToOne
    private StatementEntity statementEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProjectVersionEntity getProject() {
        return project;
    }

    public void setProject(ProjectVersionEntity project) {
        this.project = project;
    }

    public StatementEntity getStatementEntity() {
        return statementEntity;
    }

    public void setStatementEntity(StatementEntity statementEntity) {
        this.statementEntity = statementEntity;
    }
}
