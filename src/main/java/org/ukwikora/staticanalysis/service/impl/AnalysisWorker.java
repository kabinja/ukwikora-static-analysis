package org.ukwikora.staticanalysis.service.impl;

import org.ukwikora.builder.Builder;
import org.ukwikora.gitloader.GitEngine;
import org.ukwikora.gitloader.GitEngineFactory;
import org.ukwikora.gitloader.git.LocalRepo;
import org.ukwikora.model.Project;
import org.ukwikora.staticanalysis.api.StrategyRest;
import org.ukwikora.staticanalysis.monitoring.State;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class AnalysisWorker implements Runnable {
    private final AnalysisServiceImpl service;
    private StrategyRest strategy;

    AnalysisWorker(AnalysisServiceImpl service) {
        this.service = service;
    }

    @Override
    public void run() {
        try{
            service.updateState(State.Cloning);
            Set<LocalRepo> LocalRepos = cloneProjects();
            service.updateState(State.Analyzing);
            Set<Project> projects = buildProjects(LocalRepos);
            service.updateState(State.Saving);
            this.service.update(projects);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            service.updateState(State.Ready);
        }
    }

    void setStrategy(StrategyRest strategy) {
        this.strategy = strategy;
    }

    private Set<LocalRepo> cloneProjects() throws Exception {
        Set<LocalRepo> localRepos;

        final GitEngine git = GitEngineFactory.create(strategy.getApi());
        git.setUrl(strategy.getUrl());
        git.setToken(strategy.getToken());

        switch (strategy.getLocator()){
            case ByGroup:
                localRepos = git.cloneProjectsFromGroup(strategy.getGroupName());
                break;
            case ByUserName:
                localRepos = git.cloneProjectsFromUser(strategy.getUsername());
                break;
            case ByUrl:
            default:
                throw new Exception("Invalid configuration, missing source for text to analyze");
        }

        return localRepos;
    }

    private Set<Project> buildProjects(Set<LocalRepo> localRepos){
        Set<File> locations = localRepos.stream().map(LocalRepo::getLocation).collect(Collectors.toSet());
        Set<Project> projects = Builder.build(locations, true);

        Map<File, LocalRepo> mapping = new HashMap<>(localRepos.size());
        for(LocalRepo localRepo: localRepos){
            mapping.put(localRepo.getLocation(), localRepo);
        }

        for(Project project: projects){
            LocalRepo localRepo = mapping.get(project.getRootFolder());

            if(localRepo == null){
                continue;
            }

            project.setGitUrl(localRepo.getRemoteUrl());
            project.setDate(localRepo.getDate());
            project.setCommitId(localRepo.getCommitId());
        }

        return projects;
    }
}
