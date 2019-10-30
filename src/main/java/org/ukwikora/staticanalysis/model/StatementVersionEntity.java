package org.ukwikora.staticanalysis.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "statements_version")
public class StatementVersionEntity extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "project_version_id")
    private ProjectVersionEntity project;

    @ManyToOne
    @JoinColumn(name = "statement_id")
    private StatementEntity statementEntity;

    @Column(name = "dead_code")
    private boolean deadCode;

    @OneToMany(mappedBy = "statement", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ViolationEntity> violations;

    @ManyToMany
    @JoinTable(name = "clone_cluster_statements",
            joinColumns = @JoinColumn(name = "statement_version_id"),
            inverseJoinColumns = @JoinColumn(name = "clone_cluster_id"))
    private Set<StatementVersionEntity> statements;

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

    public boolean isDeadCode() {
        return deadCode;
    }

    public void setDeadCode(boolean deadCode) {
        this.deadCode = deadCode;
    }
}
