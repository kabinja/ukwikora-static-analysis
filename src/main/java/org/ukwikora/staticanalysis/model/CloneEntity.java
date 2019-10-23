package org.ukwikora.staticanalysis.model;

import javax.persistence.*;

public class CloneEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @ManyToOne
    private StatementVersionEntity statement;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "cluster", nullable = false)
    private String cluster;
}
