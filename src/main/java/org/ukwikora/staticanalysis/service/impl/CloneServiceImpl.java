package org.ukwikora.staticanalysis.service.impl;

import org.springframework.stereotype.Service;
import org.ukwikora.analytics.CloneCluster;
import org.ukwikora.analytics.Clones;
import org.ukwikora.model.UserKeyword;
import org.ukwikora.staticanalysis.service.CloneService;
import org.ukwikora.staticanalysis.service.ProjectEntityMap;


@Service
public class CloneServiceImpl implements CloneService {

    @Override
    public void saveClones(ProjectEntityMap projects, Clones<UserKeyword> clones) {
        for(CloneCluster<UserKeyword> cluster: clones.getClusters()){
            store(cluster, projects);
        }
    }

    private void store(CloneCluster<UserKeyword> cluster, ProjectEntityMap projects) {

    }
}
