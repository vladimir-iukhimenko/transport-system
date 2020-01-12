package com.transportsystem.backend.controller;

import com.transportsystem.backend.model.Engine;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Engine readEngine(@PathVariable("id") int id) {
        Engine engine = engineService.getEngineById(id);
        return engine;
    }
    //TODO: What to do with this?
    @GetMapping("/engines/add")
    public ModelAndView addEngine()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("engines/editorengines");
        return modelAndView;
    }

    @PostMapping("/engines/add")
    public ModelAndView addEngine(@ModelAttribute("engine") Engine engine)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/engines");
        engineService.addEngine(engine);
        return modelAndView;
    }

    @GetMapping("/engines/edit/{id}")
    public ModelAndView editEngine(@PathVariable("id") int id)
    {
        Engine engine = engineService.getEngineById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("engines/editorengines");
        modelAndView.addObject("engine", engine);
        return modelAndView;
    }

    @PostMapping("/engines/edit")
    public ModelAndView editEngine(@ModelAttribute("engine") Engine engine)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/engines");
        engineService.editEngine(engine);
        return modelAndView;
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
