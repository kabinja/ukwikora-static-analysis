package org.ukwikora.staticanalysis.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ukwikora.model.*;
import org.ukwikora.staticanalysis.api.StrategyRest;
import org.ukwikora.staticanalysis.monitoring.State;
import org.ukwikora.staticanalysis.monitoring.StatusMonitor;
import org.ukwikora.staticanalysis.service.AnalysisService;
import org.ukwikora.staticanalysis.service.ProjectService;

import java.util.Set;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    private final Logger logger = LogManager.getLogger(AnalysisServiceImpl.class);
    private final AnalysisWorker worker;
    private final StatusMonitor monitor;
    private final ProjectService projectService;

    @Autowired
    public AnalysisServiceImpl(ProjectService projectService) {
        this.projectService = projectService;
        this.worker = new AnalysisWorker(this);
        this.monitor = new StatusMonitor();
    }

    @Override
    public State getState() {
        return this.monitor.getState();
    }

    @Override
    public State analyze(StrategyRest rest) {
        if(monitor.isReady()){
            monitor.setState(State.Running);
            this.worker.setStrategy(rest);
            Thread thread = new Thread(this.worker, "Analysis");
            thread.start();
        }

        return monitor.getState();
    }

    @Override
    public void update(Set<Project> projects) {
        logger.info("Number of projects analyzed: " + projects.size());

        try {
            projectService.saveProjects(projects);
        } catch (Exception e) {
            logger.error(String.format("Failed to save project: %s", e.getMessage()));
        }
    }

    void updateState(State state){
        monitor.setState(state);
        logger.info(monitor.getState().toString());
    }
}
