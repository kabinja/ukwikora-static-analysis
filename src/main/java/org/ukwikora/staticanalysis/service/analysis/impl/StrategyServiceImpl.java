package org.ukwikora.staticanalysis.service.analysis.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ukwikora.staticanalysis.api.StrategyRest;
import org.ukwikora.staticanalysis.model.StrategyEntity;
import org.ukwikora.staticanalysis.repository.StrategyRepository;
import org.ukwikora.staticanalysis.service.analysis.StrategyService;

import java.util.Optional;
import java.util.Set;

@Service
public class StrategyServiceImpl implements StrategyService {
    private final StrategyRepository strategyRepository;

    @Autowired
    public StrategyServiceImpl(StrategyRepository strategyRepository) {
        this.strategyRepository = strategyRepository;
    }

    @Override
    public StrategyRest getStrategy(long id) {
        final Optional<StrategyEntity> entity = this.strategyRepository.findById(id);

        if(entity.isPresent()){
            final StrategyRest rest = new StrategyRest();
            BeanUtils.copyProperties(entity.get(), rest);

            return rest;
        }

        return null;
    }

    @Override
    public StrategyRest getCurrentStrategy() {
        final Optional<StrategyEntity> entity = this.strategyRepository.findTopByOrderByActivationDesc();

        if(entity.isPresent()){
            final StrategyRest rest = new StrategyRest();
            BeanUtils.copyProperties(entity.get(), rest);

            return rest;
        }

        return null;
    }

    @Override
    public StrategyRest createStrategy(StrategyRest rest) {
        StrategyEntity entity = new StrategyEntity();
        BeanUtils.copyProperties(rest, entity);

        entity = this.strategyRepository.save(entity);
        BeanUtils.copyProperties(entity, rest);

        return rest;
    }

    @Override
    public Set<StrategyRest> getAllStrategies() {
        return null;
    }
}
