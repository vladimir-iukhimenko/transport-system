package com.transportsystem.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transportsystem.backend.repository.EngineRepository;
import com.transportsystem.backend.model.Engine;

import java.util.List;

/**
 * Created by DZCKJS0 on 28.11.2019.
 */
@Service
public class EngineService {
    private EngineRepository engineRepository;

    @Autowired
    public void setEngineDAO(EngineRepository engineRepository) {this.engineRepository = engineRepository;}

    @Transactional
    public void addEngine(Engine engine)
    {
        engineRepository.save(engine);
    }

    @Transactional
    public void deleteEngine(Engine engine) { engineRepository.delete(engine);}

    @Transactional
    public void editEngine(Engine engine)
    {
        engineRepository.save(engine);
    }

    @Transactional
    public Engine getEngineById(int id) {return engineRepository.findById(id).orElse(null);}

    @Transactional
    public List<Engine> getAllEngines() {return engineRepository.findAll();}

}
