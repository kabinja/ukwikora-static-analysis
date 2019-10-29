package org.ukwikora.staticanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ukwikora.staticanalysis.api.StrategyRest;
import org.ukwikora.staticanalysis.service.AnalysisService;
import org.ukwikora.staticanalysis.monitoring.State;
import org.ukwikora.staticanalysis.service.StrategyService;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/analysis")
public class UpdateController {
    private final StrategyService strategyService;
    private final AnalysisService analysisService;

    @Autowired
    public UpdateController(StrategyService strategyService, AnalysisService analysisService) {
        this.strategyService = strategyService;
        this.analysisService = analysisService;
    }

    @PostMapping("/run")
    public State run(){
        StrategyRest strategy = this.strategyService.getCurrentStrategy();
        return analysisService.analyze(strategy);
    }

    @GetMapping("/state")
    public State getState(){
        return this.analysisService.getState();
    }

    @GetMapping("/strategies")
    public Set<StrategyRest> getStrategies(){
        return this.strategyService.getAllStrategies();
    }

    @GetMapping("/strategies/current")
    public StrategyRest getCurrentStrategy(){
        return this.strategyService.getCurrentStrategy();
    }

    @PostMapping("/strategies")
    public StrategyRest createStrategy(@Valid @RequestBody StrategyRest strategy){
        return this.strategyService.createStrategy(strategy);
    }
}
