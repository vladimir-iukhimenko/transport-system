package com.transportsystem.backend.controller;

import com.transportsystem.backend.model.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.transportsystem.backend.model.TransportModel;
import com.transportsystem.backend.service.EngineService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EngineRESTController {

    private EngineService engineService;

    @Autowired
    public void setEngineService(EngineService engineService) {this.engineService = engineService;}

    @RequestMapping(value = "/engines", method = RequestMethod.GET)
    public List<Engine> listEngines() {
        List<Engine> engines = engineService.getAllEngines();
        return engines;
    }

    @RequestMapping(value = "/engines/{id}", method = RequestMethod.GET)
    public Engine getEngine(@PathVariable("id") int id) {
        Engine engine = engineService.getEngineById(id);
        return engine;
    }

    @RequestMapping(value = "/engines/add", method = RequestMethod.GET)
    public ModelAndView addEngine()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("engines/editorengines");
        return modelAndView;
    }

    @RequestMapping(value = "/engines/add", method = RequestMethod.POST)
    public ModelAndView addEngine(@ModelAttribute("engine") Engine engine)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/engines");
        engineService.addEngine(engine);
        return modelAndView;
    }

    @RequestMapping(value = "/engines/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editEngine(@PathVariable("id") int id)
    {
        Engine engine = engineService.getEngineById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("engines/editorengines");
        modelAndView.addObject("engine", engine);
        return modelAndView;
    }

    @RequestMapping(value = "/engines/edit", method = RequestMethod.POST)
    public ModelAndView editEngine(@ModelAttribute("engine") Engine engine)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/engines");
        engineService.editEngine(engine);
        return modelAndView;
    }

    @RequestMapping(value = "/engines/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEngine(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/engines");
        Engine engine = engineService.getEngineById(id);
        engineService.deleteEngine(engine);
        return modelAndView;
    }

    @RequestMapping(value = "/boundedtransportmodels/{id}", method = RequestMethod.GET)
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
