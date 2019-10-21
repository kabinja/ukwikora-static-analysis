package org.ukwikora.staticanalysis.analysis;

import org.ukwikora.model.Project;

import java.util.List;

public interface AnalysisService {
    void analyze();
    void update(List<Project> projects);
}
