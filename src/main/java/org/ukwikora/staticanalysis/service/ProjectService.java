package org.ukwikora.staticanalysis.service;

import org.ukwikora.model.Project;

import java.util.Set;

public interface ProjectService {
    void saveProjects(Set<Project> projects) throws Exception;
}
