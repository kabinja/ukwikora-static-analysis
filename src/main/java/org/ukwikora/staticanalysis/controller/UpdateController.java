package org.ukwikora.staticanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ukwikora.staticanalysis.analysis.AnalysisService;
import org.ukwikora.staticanalysis.model.StrategyEntity;
import org.ukwikora.staticanalysis.model.StrategyRepository;
import org.ukwikora.staticanalysis.monitoring.State;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UpdateController {
    private final StrategyRepository strategyRepository;
    private final AnalysisService analysisService;

    @Autowired
    public UpdateController(StrategyRepository strategyRepository, AnalysisService analysisService) {
        this.strategyRepository = strategyRepository;
        this.analysisService = analysisService;
    }

    @PostMapping("analysis/run")
    public State update(){
        StrategyEntity strategyEntity = this.strategyRepository.findTopByOrderByActivationDesc();
        return analysisService.analyze(strategyEntity);
    }

    @GetMapping("analysis/state")
    public State getState(){
        return this.analysisService.getState();
    }

    @GetMapping("analysis/strategies")
    public List<StrategyEntity> getStrategies(){
        return strategyRepository.findAll();
    }

    @GetMapping("analysis/strategies/current")
    public StrategyEntity getCurrentStrategy(){
        return strategyRepository.findTopByOrderByActivationDesc();
    }

    @PostMapping("analysis/strategy")
    public StrategyEntity setConfiguration(@Valid @RequestBody StrategyEntity strategyEntity){
        return strategyRepository.save(strategyEntity);
    }
}
