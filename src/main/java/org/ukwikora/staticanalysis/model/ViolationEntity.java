package org.ukwikora.staticanalysis.model;

import javax.persistence.*;

@Entity
@Table(name = "violations")
public class ViolationEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @ManyToOne
    private StatementVersionEntity statement;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "message")
    private String message;

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
