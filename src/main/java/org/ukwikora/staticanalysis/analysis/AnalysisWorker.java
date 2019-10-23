package org.ukwikora.staticanalysis.analysis;

import org.ukwikora.builder.Builder;
import org.ukwikora.gitloader.GitEngine;
import org.ukwikora.gitloader.GitEngineFactory;
import org.ukwikora.gitloader.git.LocalRepo;
import org.ukwikora.model.Project;
import org.ukwikora.staticanalysis.model.StrategyEntity;
import org.ukwikora.staticanalysis.monitoring.State;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AnalysisWorker implements Runnable {
    private final AnalysisServiceImpl service;
    private StrategyEntity strategyEntity;

    AnalysisWorker(AnalysisServiceImpl service) {
        this.service = service;
    }

    @Override
    public void run() {
        try{
            service.updateState(State.Cloning);
            Set<LocalRepo> LocalRepos = cloneProjects();
            service.updateState(State.Analyzing);
            List<Project> projects = buildProjects(LocalRepos);
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

    void setStrategyEntity(StrategyEntity strategyEntity) {
        this.strategyEntity = strategyEntity;
    }

    private Set<LocalRepo> cloneProjects() throws Exception {
        Set<LocalRepo> localRepos;

        final GitEngine git = GitEngineFactory.create(strategyEntity.getApi());
        git.setUrl(strategyEntity.getUrl());
        git.setToken(strategyEntity.getToken());

        switch (strategyEntity.getLocator()){
            case ByGroup:
                localRepos = git.cloneProjectsFromGroup(strategyEntity.getGroupName());
                break;
            case ByUserName:
                localRepos = git.cloneProjectsFromUser(strategyEntity.getUsername());
                break;
            case ByUrl:
            default:
                throw new Exception("Invalid configuration, missing source for text to analyze");
        }

        return localRepos;
    }

    private List<Project> buildProjects(Set<LocalRepo> localRepos){
        Set<File> locations = localRepos.stream().map(LocalRepo::getLocation).collect(Collectors.toSet());
        List<Project> projects = Builder.build(locations, true);

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
