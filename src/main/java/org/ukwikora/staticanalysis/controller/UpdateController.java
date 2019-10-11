package org.ukwikora.staticanalysis.controller;

import org.springframework.web.bind.annotation.*;
import org.ukwikora.staticanalysis.model.Strategy;
import org.ukwikora.staticanalysis.model.StrategyRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UpdateController {
    private final StrategyRepository strategyRepository;

    public UpdateController(StrategyRepository strategyRepository) {
        this.strategyRepository = strategyRepository;
    }

    @PostMapping("update")
    public void update(){

    }

    @GetMapping("update/strategy")
    public Strategy getStrategy(){
        return strategyRepository.findTopByOrderByActivationDesc();
    }

    @PostMapping("update/strategy")
    public Strategy setConfiguration(@Valid @RequestBody Strategy strategy){
        return strategyRepository.save(strategy);
    }
}
