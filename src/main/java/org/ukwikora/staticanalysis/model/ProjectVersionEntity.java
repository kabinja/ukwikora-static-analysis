package org.ukwikora.staticanalysis.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "projects_version")
public class ProjectVersionEntity extends AbstractEntity{
    @Column(name = "commit_id")
    private String commitId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "commit_date")
    private Date commitDate;
    @Column(name = "lines_of_code")
    private int linesOfCode;
    @Column(name = "dead_code")
    private int deadCode;
    @Column(name = "clone_type_1")
    private float cloneType1;
    @Column(name = "clone_type_2")
    private float cloneType2;
    @Column(name = "clone_type_3")
    private float cloneType3;
    @Column(name = "clone_type_4")
    private float cloneType4;
    @ManyToOne
    private ProjectEntity projectEntity;
    @ManyToMany(targetEntity = ProjectVersionEntity.class, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "dependencies",
            joinColumns = @JoinColumn(name = "target"),
            inverseJoinColumns = @JoinColumn(name = "source"))
    private Set<ProjectVersionEntity> dependencies;

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

    public float getCloneType1() {
        return cloneType1;
    }

    public void setCloneType1(float cloneType1) {
        this.cloneType1 = cloneType1;
    }

    public float getCloneType2() {
        return cloneType2;
    }

    public void setCloneType2(float cloneType2) {
        this.cloneType2 = cloneType2;
    }

    public float getCloneType3() {
        return cloneType3;
    }

    public void setCloneType3(float cloneType3) {
        this.cloneType3 = cloneType3;
    }

    public float getCloneType4() {
        return cloneType4;
    }

    public void setCloneType4(float cloneType4) {
        this.cloneType4 = cloneType4;
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public Set<ProjectVersionEntity> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Set<ProjectVersionEntity> dependencies) {
        this.dependencies = dependencies;
    }
}
