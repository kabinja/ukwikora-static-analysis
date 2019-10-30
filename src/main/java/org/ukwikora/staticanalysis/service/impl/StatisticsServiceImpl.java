package org.ukwikora.staticanalysis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ukwikora.model.Project;
import org.ukwikora.staticanalysis.model.ProjectStatisticsEntity;
import org.ukwikora.staticanalysis.model.ProjectVersionEntity;
import org.ukwikora.staticanalysis.repository.ProjectStatisticsRepository;
import org.ukwikora.staticanalysis.repository.ProjectVersionRepository;
import org.ukwikora.staticanalysis.service.ProjectEntityMap;
import org.ukwikora.staticanalysis.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final ProjectStatisticsRepository statisticsRepository;
    private final ProjectVersionRepository projectVersionRepository;

    @Autowired
    public StatisticsServiceImpl(ProjectStatisticsRepository statisticsRepository, ProjectVersionRepository projectVersionRepository) {
        this.statisticsRepository = statisticsRepository;
        this.projectVersionRepository = projectVersionRepository;
    }

    @Override
    public void saveStatistics(ProjectEntityMap projectsMap) {
        for(Project project: projectsMap.getProjects()){
            createIfNotExit(project, projectsMap);
        }
    }

    private void createIfNotExit(Project project, ProjectEntityMap projectsMap) {
        ProjectVersionEntity projectVersionEntity = projectsMap.getProjectVersionEntity(project);

        if(statisticsRepository.findByProjectVersionEntity(projectVersionEntity).isPresent()){
            return;
        }

        ProjectStatisticsEntity statisticsEntity = new ProjectStatisticsEntity();
        statisticsEntity.setProjectVersionEntity(projectVersionEntity);
        statisticsEntity.setLinesOfCode(project.getLoc());
        statisticsEntity.setDeadCode(project.getDeadLoc());

        statisticsEntity = statisticsRepository.save(statisticsEntity);

        projectVersionEntity.setStatisticsEntity(statisticsEntity);
        projectVersionEntity = projectVersionRepository.save(projectVersionEntity);
        projectsMap.setProjectVersionEntity(project, projectVersionEntity);
    }
}
