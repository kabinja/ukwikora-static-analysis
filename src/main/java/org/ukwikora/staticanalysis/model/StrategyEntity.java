package org.ukwikora.staticanalysis.model;

import org.hibernate.annotations.UpdateTimestamp;
import org.ukwikora.gitloader.Api;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "strategies")
public class StrategyEntity extends AbstractEntity{
    public enum Locator {
        ByGroup, ByUserName, ByUrl
    }

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name= "api", nullable = false)
    private Api api;

    @Column(name = "locator", nullable = false)
    private Locator locator;

    @Column(nullable = false)
    private String url;

    @Column(name = "token")
    private String token;

    @Column(name = "user_name")
    private String username;

    @Column(name = "group_name")
    private String groupName;

    @ElementCollection
    @CollectionTable(name = "project_names")
    private List<String> projectNames;

    @UpdateTimestamp()
    private Date activation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public Locator getLocator() {
        return locator;
    }

    public void setLocator(Locator locator) {
        this.locator = locator;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(List<String> projectNames) {
        this.projectNames = projectNames;
    }

    public Date getActivation() {
        return activation;
    }

    public void setActivation(Date activation) {
        this.activation = activation;
    }
}
