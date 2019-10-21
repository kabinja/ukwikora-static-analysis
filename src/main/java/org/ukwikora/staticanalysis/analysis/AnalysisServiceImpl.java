package org.ukwikora.staticanalysis.analysis;

import org.springframework.stereotype.Service;
import org.ukwikora.model.Project;
import org.ukwikora.staticanalysis.model.ProjectRepository;
import org.ukwikora.staticanalysis.model.Strategy;
import org.ukwikora.staticanalysis.model.StrategyRepository;

import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    private final AnalysisWorker worker;
    private final ProjectRepository projectRepository;
    private final StatusMonitor monitor;

    public AnalysisServiceImpl(ProjectRepository projectRepository, StrategyRepository strategyRepository) {
        final Strategy currentStrategy = strategyRepository.findTopByOrderByActivationDesc();

        this.worker = new AnalysisWorker(this, currentStrategy);
        this.projectRepository = projectRepository;
        this.monitor = new StatusMonitor();
    }

    @Override
    public void analyze() {
        Thread thread = new Thread(this.worker);
        thread.start();
    }

    @Override
    public void update(List<Project> projects) {

    }

    void updateState(StatusMonitor.State state){

    }
}
