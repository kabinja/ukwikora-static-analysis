package org.ukwikora.staticanalysis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projects_statistics")
public class ProjectStatisticsEntity extends AbstractEntity {
    @OneToOne
    private ProjectVersionEntity projectVersionEntity;

    @Column(name = "lines_of_code")
    private int linesOfCode;

    @Column(name = "dead_code")
    private int deadCode;

    public ProjectVersionEntity getProjectVersionEntity() {
        return projectVersionEntity;
    }

    public void setProjectVersionEntity(ProjectVersionEntity projectVersionEntity) {
        this.projectVersionEntity = projectVersionEntity;
    }

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public int getDeadCode() {
        return deadCode;
    }

    public void setDeadCode(int deadCode) {
        this.deadCode = deadCode;
    }


}
