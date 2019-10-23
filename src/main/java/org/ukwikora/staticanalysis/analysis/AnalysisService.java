package org.ukwikora.staticanalysis.analysis;

import org.ukwikora.model.Project;
import org.ukwikora.staticanalysis.model.StrategyEntity;
import org.ukwikora.staticanalysis.monitoring.State;

import java.util.List;

public interface AnalysisService {
    State getState();
    State analyze(StrategyEntity strategyEntity);
    void update(List<Project> projects);

}
