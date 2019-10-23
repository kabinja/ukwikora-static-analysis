package org.ukwikora.staticanalysis.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "projects_version")
public class ProjectVersionEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(name = "commit_id")
    private String commitId;
    @Column(name = "commit_date")
    private Date commitDate;
    @Column(name = "lines_of_code")
    private int linesOfCode;
    @Column(name = "dead_code")
    private int deadCode;
    @ManyToOne
    private ProjectEntity projectEntity;
    @ManyToMany(targetEntity = ProjectVersionEntity.class, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "dependencies",
            joinColumns = @JoinColumn(name = "target"),
            inverseJoinColumns = @JoinColumn(name = "source"))
    private Set dependencies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
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

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }
}
