package org.ukwikora.staticanalysis.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects_version")
public class ProjectVersionEntity extends AbstractEntity{
    @Column(name = "commit_id")
    private String commitId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "commit_date")
    private Date commitDate;

    @ManyToOne
    private ProjectEntity projectEntity;

    @OneToOne
    private ProjectStatisticsEntity statisticsEntity;

    @ManyToMany
    @JoinTable(name = "dependencies",
        joinColumns = @JoinColumn(name = "parent"),
        inverseJoinColumns = @JoinColumn(name = "child")
    )
    private Set<ProjectVersionEntity> children;

    @ManyToMany
    @JoinTable(name = "dependencies",
            joinColumns = @JoinColumn(name = "child"),
            inverseJoinColumns = @JoinColumn(name = "parent")
    )
    private Set<ProjectVersionEntity> parents;

    public ProjectVersionEntity(){
        this.children = new HashSet<>();
        this.parents = new HashSet<>();
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

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public ProjectStatisticsEntity getStatisticsEntity() {
        return statisticsEntity;
    }

    public void setStatisticsEntity(ProjectStatisticsEntity statisticsEntity) {
        this.statisticsEntity = statisticsEntity;
    }

    public Set<ProjectVersionEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<ProjectVersionEntity> children) {
        this.children = children;
    }

    public void addChild(ProjectVersionEntity child) {
        this.children.add(child);
    }

    public Set<ProjectVersionEntity> getParents() {
        return parents;
    }

    public void setParents(Set<ProjectVersionEntity> parents) {
        this.parents = parents;
    }

    public void addParent(ProjectVersionEntity parent) {
        this.parents.add(parent);
    }
}
