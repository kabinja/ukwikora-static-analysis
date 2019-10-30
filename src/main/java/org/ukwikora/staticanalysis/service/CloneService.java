package org.ukwikora.staticanalysis.service;

import org.ukwikora.analytics.Clones;
import org.ukwikora.model.UserKeyword;

public interface CloneService {
    void saveClones(ProjectEntityMap projects, Clones<UserKeyword> clones);
}
