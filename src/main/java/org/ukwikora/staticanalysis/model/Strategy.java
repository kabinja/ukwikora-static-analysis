package org.ukwikora.staticanalysis.model;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "strategies")
public class Strategy {
    public enum Type{
        Local, Gitlab, Git
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Type type;
    @ElementCollection
    @CollectionTable(name="project_locations")
    private List<String> projectLocations;
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

    public Type getType() {
        return type;
    }

    public List<String> getProjectLocations() {
        return projectLocations;
    }

    public String getGroupName() {
        return groupName;
    }

    public Date getActivation() {
        return activation;
    }
}
