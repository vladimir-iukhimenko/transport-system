package com.transportsystem.backend.controller;

import com.transportsystem.backend.model.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.transportsystem.backend.model.TransportModel;
import com.transportsystem.backend.service.EngineService;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
@CrossOrigin(origins = {"http://localhost:8080"})
public class EngineRESTController {

    private EngineService engineService;

    @Autowired
    public void setEngineService(EngineService engineService) {this.engineService = engineService;}

    @GetMapping("/engines")
    public List<Engine> readAllEngines() {
        List<Engine> engines = engineService.getAllEngines();
        return engines;
    }

    @GetMapping("/engines/{id}")
    public Engine readEngine(@PathVariable int id) {
        Engine engine = engineService.getEngineById(id);
        return engine;
    }

    @PostMapping("/engines/create")
    public ResponseEntity<Void> createEngine(@RequestBody Engine engine)
    {
        engineService.addEngine(engine);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/engines/update/{id}")
    public ResponseEntity<Engine> updateEngine(@PathVariable int id, @RequestBody Engine engine)
    {
        engineService.editEngine(engine);
        return new ResponseEntity<>(engine, HttpStatus.OK);
    }

    @DeleteMapping("/engines/delete/{id}")
    public ResponseEntity<Void> deleteEngine(@PathVariable("id") int id)
    {
        Engine engine = engineService.getEngineById(id);
        if(engine == null) return ResponseEntity.notFound().build();
        engineService.deleteEngine(engine);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/boundedtransportmodels/{id}")
    public ModelAndView listBoundedTransportModels(@PathVariable("id") int id)
    {
        Engine engine = engineService.getEngineById(id);
        List<TransportModel> transportModels = engine.getTransportmodels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transportmodels/transportmodels");
        modelAndView.addObject("transportmodels", transportModels);
        return modelAndView;
    }
}
