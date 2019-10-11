package org.ukwikora.staticanalysis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ukwikora.staticanalysis.model.Project;
import org.ukwikora.staticanalysis.model.ProjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("projects")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("project/{id}")
    public Optional<Project> getProject(@PathVariable(value = "id") long id) {
        return projectRepository.findById(id);
    }
}
