package org.ukwikora.staticanalysis.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clone_clusters")
public class CloneClusterEntity extends AbstractEntity {
    @Column
    private String type;

    @ManyToMany
    @JoinTable(name = "clone_cluster_statements",
            joinColumns = @JoinColumn(name = "clone_cluster_id"),
            inverseJoinColumns = @JoinColumn(name = "statement_version_id"))
    private Set<StatementVersionEntity> statements;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<StatementVersionEntity> getStatements() {
        return statements;
    }

    public void setStatement(Set<StatementVersionEntity> statements) {
        this.statements = statements;
    }
}
