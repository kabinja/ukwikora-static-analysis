package org.ukwikora.staticanalysis.model;

import org.hibernate.annotations.UpdateTimestamp;
import org.ukwikora.gitloader.Api;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "strategies")
public class Strategy {
    public enum Fetch{
        ByGroup, ByUserName, ByUrl
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column
    private Api api;
    @Column(nullable = false)
    private Fetch fetch;
    @Column(nullable = false)
    private String url;
    @Column
    private String token;
    @Column
    private String username;
    @Column
    private String groupName;
    @UpdateTimestamp
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

    public Fetch getFetch() {
        return fetch;
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
