package org.ukwikora.staticanalysis.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class DeadCodeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @ManyToOne
    private StatementVersionEntity statement;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StatementVersionEntity getStatement() {
        return statement;
    }

    public void setStatement(StatementVersionEntity statement) {
        this.statement = statement;
    }
}
