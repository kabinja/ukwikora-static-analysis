package org.ukwikora.staticanalysis.analysis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ukwikora.model.Project;
import org.ukwikora.staticanalysis.model.ProjectRepository;
import org.ukwikora.staticanalysis.model.Strategy;
import org.ukwikora.staticanalysis.monitoring.State;
import org.ukwikora.staticanalysis.monitoring.StatusMonitor;

import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    private final Logger logger = LogManager.getLogger(AnalysisServiceImpl.class);
    private final AnalysisWorker worker;
    private final ProjectRepository projectRepository;
    private final StatusMonitor monitor;

    @Autowired
    public AnalysisServiceImpl(ProjectRepository projectRepository) {
        this.worker = new AnalysisWorker(this);
        this.projectRepository = projectRepository;
        this.monitor = new StatusMonitor();
    }

    @Override
    public State getState() {
        return this.monitor.getState();
    }

    @Override
    public State analyze(Strategy strategy) {
        if(monitor.isReady()){
            monitor.setState(State.Running);
            this.worker.setStrategy(strategy);
            Thread thread = new Thread(this.worker, "Analysis");
            thread.start();
        }

        return monitor.getState();
    }

    @Override
    public void update(List<Project> projects) {
        logger.info(projects.size());
        updateState(State.Ready);
    }

    void updateState(State state){
        monitor.setState(state);
        logger.info(monitor.getState().toString());
    }
}
