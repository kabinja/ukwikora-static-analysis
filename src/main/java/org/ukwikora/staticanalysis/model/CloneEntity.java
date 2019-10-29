package org.ukwikora.staticanalysis.model;

import javax.persistence.*;

@Entity
@Table(name = "clones")
public class CloneEntity extends AbstractEntity {
    @Column
    private String type;
    @Column
    private long cluster;
    @ManyToOne
    @JoinColumn(name = "statement_version_id", nullable = false)
    private StatementVersionEntity statement;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getCluster() {
        return cluster;
    }

    public void setCluster(long cluster) {
        this.cluster = cluster;
    }

    public StatementVersionEntity getStatement() {
        return statement;
    }

    public void setStatement(StatementVersionEntity statement) {
        this.statement = statement;
    }
}
