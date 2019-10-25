package org.ukwikora.staticanalysis.api;

import org.ukwikora.gitloader.Api;
import org.ukwikora.staticanalysis.model.StrategyEntity;

import java.util.Date;
import java.util.List;

public class StrategyRest {
    private Long id;
    private String name;
    private Api api;
    private StrategyEntity.Locator locator;
    private String url;
    private String token;
    private String username;
    private String groupName;
    private List<String> projectNames;
    private Date activation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public StrategyEntity.Locator getLocator() {
        return locator;
    }

    public void setLocator(StrategyEntity.Locator locator) {
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
