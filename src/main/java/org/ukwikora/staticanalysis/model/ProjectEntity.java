package org.ukwikora.staticanalysis.model;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class ProjectEntity extends AbstractEntity {
    @Column(name = "slug", nullable = false)
    private String slug;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "url")
    private String url;

    public ProjectEntity(){
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
