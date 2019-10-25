package org.ukwikora.staticanalysis.service.analysis;

import org.ukwikora.model.Project;
import org.ukwikora.staticanalysis.api.StrategyRest;
import org.ukwikora.staticanalysis.monitoring.State;

import java.util.List;

public interface AnalysisService {
    State getState();
    State analyze(StrategyRest strategy);
    void update(List<Project> projects);

}
