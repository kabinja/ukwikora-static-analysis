package org.ukwikora.staticanalysis.model;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(name = "uri", nullable = false)
    private final String uri;
    @Column(name = "name", nullable = false)
    private final String name;
    @Column(name = "git_url")
    private String gitUrl;
    @Column(name = "loc")
    private int loc;
    @Column(name="dead_code")
    private int deadCode;

    public Project(String name, String uri){
        this.name = name;
        this.uri = uri;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public int getLoc() {
        return loc;
    }

    public int getDeadCode() {
        return deadCode;
    }
}
