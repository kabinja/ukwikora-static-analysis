package org.ukwikora.staticanalysis.service;

import org.ukwikora.analytics.Clones;
import org.ukwikora.model.Project;
import org.ukwikora.model.UserKeyword;
import org.ukwikora.staticanalysis.api.StrategyRest;
import org.ukwikora.staticanalysis.monitoring.State;

import java.util.Set;

public interface AnalysisService {
    State getState();
    State analyze(StrategyRest strategy);
    void update(Set<Project> projects, Clones<UserKeyword> clones);

}
