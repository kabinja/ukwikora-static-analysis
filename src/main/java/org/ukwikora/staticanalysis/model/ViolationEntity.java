package org.ukwikora.staticanalysis.model;

import javax.persistence.*;

@Entity
@Table(name = "violations")
public class ViolationEntity extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "statement_version_id", nullable = false)
    private StatementVersionEntity statement;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "message")
    private String message;

    public StatementVersionEntity getStatement() {
        return statement;
    }

    public void setStatement(StatementVersionEntity statement) {
        this.statement = statement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
