package com.transportsystem.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transportsystem.backend.dao.EngineDAO;
import com.transportsystem.backend.model.Engine;

import java.util.List;

/**
 * Created by DZCKJS0 on 28.11.2019.
 */
@Service
public class EngineService {
    private EngineDAO engineDAO;

    @Autowired
    public void setEngineDAO(EngineDAO engineDAO) {this.engineDAO = engineDAO;}

    @Transactional
    public void addEngine(Engine engine)
    {
        engineDAO.addEngine(engine);
    }

    @Transactional
    public void deleteEngine(Engine engine) { engineDAO.deleteEngine(engine);}

    @Transactional
    public void editEngine(Engine engine)
    {
        engineDAO.editEngine(engine);
    }

    @Transactional
    public Engine getEngineById(int id) {return engineDAO.getEngineById(id);}

    @Transactional
    public List<Engine> getAllEngines() {return engineDAO.getAllEngines();}

}
