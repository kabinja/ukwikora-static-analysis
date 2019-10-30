package org.ukwikora.staticanalysis.service;

import org.ukwikora.model.Project;

import java.util.Set;

public interface ProjectService {
    ProjectEntityMap saveProjects(Set<Project> projects) throws Exception;
}
