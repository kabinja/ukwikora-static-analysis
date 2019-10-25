package org.ukwikora.staticanalysis.service.analysis;

import org.ukwikora.staticanalysis.api.StrategyRest;

import java.util.Set;

public interface StrategyService {
    StrategyRest getStrategy(long id);
    StrategyRest getCurrentStrategy();
    StrategyRest createStrategy(StrategyRest strategy);
    Set<StrategyRest> getAllStrategies();

}
