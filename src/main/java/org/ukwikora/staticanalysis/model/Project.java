package org.ukwikora.staticanalysis.model;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(name = "slug", nullable = false)
    private final String slug;
    @Column(name = "name", nullable = false)
    private final String name;
    @Column(name = "url")
    private String url;

    public Project(String name, String slug){
        this.name = name;
        this.slug = slug;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getGitUrl() {
        return url;
    }
}
