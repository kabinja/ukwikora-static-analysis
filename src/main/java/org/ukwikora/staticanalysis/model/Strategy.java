package org.ukwikora.staticanalysis.model;

import org.hibernate.annotations.UpdateTimestamp;
import org.ukwikora.gitloader.Api;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "strategies")
public class Strategy {
    public enum Locator {
        ByGroup, ByUserName, ByUrl
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Api getApi() {
        return api;
    }

    public Locator getLocator() {
        return locator;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return this.token;
    }

    public String getUsername(){
        return username;
    }

    public String getGroupName() {
        return groupName;
    }

    public Date getActivation() {
        return activation;
    }
}
