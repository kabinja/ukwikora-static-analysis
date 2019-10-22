package org.ukwikora.staticanalysis.analysis;

import org.ukwikora.builder.Builder;
import org.ukwikora.gitloader.GitEngine;
import org.ukwikora.gitloader.GitEngineFactory;
import org.ukwikora.model.Project;
import org.ukwikora.staticanalysis.model.Strategy;
import org.ukwikora.staticanalysis.monitoring.State;

import java.io.File;
import java.util.List;
import java.util.Set;

public class AnalysisWorker implements Runnable {
    private final AnalysisServiceImpl service;
    private Strategy strategy;

    AnalysisWorker(AnalysisServiceImpl service) {
        this.service = service;
    }

    @Override
    public void run() {
        try{
            service.updateState(State.Cloning);
            Set<File> location = cloneProjects();
            service.updateState(State.Analyzing);
            List<Project> projects = Builder.build(location, true);
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

    private Set<File> cloneProjects() throws Exception {
        Set<File> locations;

        final GitEngine git = GitEngineFactory.create(strategy.getApi());
        git.setUrl(strategy.getUrl());
        git.setToken(strategy.getToken());

        switch (strategy.getLocator()){
            case ByGroup:
                locations = git.cloneProjectsFromGroup(strategy.getGroupName());
                break;
            case ByUserName:
                locations = git.cloneProjectsFromUser(strategy.getUsername());
                break;
            case ByUrl:
            default:
                throw new Exception("Invalid configuration, missing source for text to analyze");
        }

        return locations;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
