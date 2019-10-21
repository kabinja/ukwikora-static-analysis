package org.ukwikora.staticanalysis.analysis;

import org.ukwikora.builder.Builder;
import org.ukwikora.gitloader.GitEngine;
import org.ukwikora.gitloader.GitEngineFactory;
import org.ukwikora.model.Project;
import org.ukwikora.staticanalysis.model.Strategy;

import java.io.File;
import java.util.List;
import java.util.Set;

public class AnalysisWorker implements Runnable {
    private final AnalysisServiceImpl service;
    private final Strategy strategy;

    AnalysisWorker(AnalysisServiceImpl service, Strategy strategy) {
        this.service = service;
        this.strategy = strategy;
    }

    @Override
    public void run() {
        try{
            service.updateState(StatusMonitor.State.Cloning);
            Set<File> location = getLocation(this.strategy);
            service.updateState(StatusMonitor.State.Analyzing);
            List<Project> projects = Builder.build(location, true);
            service.updateState(StatusMonitor.State.Saving);
            this.service.update(projects);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private Set<File> getLocation(final Strategy strategy) throws Exception {
        Set<File> locations;

        final GitEngine git = GitEngineFactory.create(strategy.getApi());
        git.setUrl(strategy.getUrl()).setToken(strategy.getToken());

        switch (strategy.getFetch()){
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
}
