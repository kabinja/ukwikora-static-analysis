package org.ukwikora.staticanalysis.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ukwikora.analytics.Clones;
import org.ukwikora.model.*;
import org.ukwikora.staticanalysis.api.StrategyRest;
import org.ukwikora.staticanalysis.monitoring.State;
import org.ukwikora.staticanalysis.monitoring.StatusMonitor;
import org.ukwikora.staticanalysis.service.*;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    private final Logger logger = LogManager.getLogger(AnalysisServiceImpl.class);
    private final AnalysisWorker worker;
    private final StatusMonitor monitor;

    private final ProjectService projectService;
    private final CloneService cloneService;
    private final StatisticsService statisticsService;

    @Autowired
    public AnalysisServiceImpl(ProjectService projectService, CloneService cloneService, StatisticsService statisticsService) {
        this.projectService = projectService;
        this.cloneService = cloneService;
        this.statisticsService = statisticsService;
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
    @Transactional
    public void update(Set<Project> projects, Clones<UserKeyword> clones) {
        logger.info("Number of projects analyzed: " + projects.size());

        try {
            final ProjectEntityMap projectEntityMap = projectService.saveProjects(projects);
            cloneService.saveClones(projectEntityMap, clones);
            statisticsService.saveStatistics(projectEntityMap);
        } catch (Exception e) {
            logger.error(String.format("Failed to save project: %s", e.getMessage()));
        }
    }

    void updateState(State state){
        monitor.setState(state);
        logger.info(monitor.getState().toString());
    }
}
