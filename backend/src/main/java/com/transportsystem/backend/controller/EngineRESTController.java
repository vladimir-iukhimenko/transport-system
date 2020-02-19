package com.transportsystem.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transportsystem.backend.model.Engine;
import com.transportsystem.backend.model.Transport;
import com.transportsystem.backend.service.JsonService;
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
@CrossOrigin(origins = "*")
public class EngineRESTController {

    private EngineService engineService;
    private JsonService jsonService;

    @Autowired
    public void setEngineService(EngineService engineService) {this.engineService = engineService;}

    @Autowired
    public void setJsonService(JsonService jsonService) {this.jsonService = jsonService;}

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

    @PostMapping(value = "/engines/add",consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Engine createEngine(@RequestBody String data) throws JsonProcessingException {
        Engine engine = jsonService.getObjectMapper().readValue(data,Engine.class);
        engineService.addEngine(engine);
        return engine;
    }

    @PutMapping(value = "/engines/edit/",consumes = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public Engine updateEngine(@RequestBody String data) throws JsonProcessingException {
        Engine engine = jsonService.getObjectMapper().readValue(data,Engine.class);
        engineService.editEngine(engine);
        return engine;
    }

    @DeleteMapping("/engines/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteEngine(@PathVariable("id") int id)
    {
        Engine engine = engineService.getEngineById(id);
        if(engine == null) return ResponseEntity.notFound().build();
        engineService.deleteEngine(engine);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/boundedtransports/{id}")
    public List<Transport> listBoundedTransports(@PathVariable("id") int id)
    {
        Engine engine = engineService.getEngineById(id);
        List<Transport> transports = engine.getTransports();
        return transports;
    }
}
