package com.transportsystem.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.transportsystem.backend.model.Engine;
import com.transportsystem.backend.model.Transport;
import com.transportsystem.backend.model.TransportModel;
import com.transportsystem.backend.service.EngineService;
import com.transportsystem.backend.service.TransportModelService;
import java.util.List;

/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Controller
public class TransportModelController {
    private TransportModelService transportModelService;

    private EngineService engineService;

    @Autowired
    public void setTransportModelService(TransportModelService transportModelService) {
        this.transportModelService = transportModelService;
    }

    @Autowired
    public void setEngineService(EngineService engineService) {
        this.engineService = engineService;
    }

    @RequestMapping(value = "/transportmodels", method = RequestMethod.GET)
    public ModelAndView listTransportModels() {
        List<TransportModel> transportmodels = transportModelService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transportmodels/transportmodels");
        modelAndView.addObject("transportmodels",transportmodels);
        return modelAndView;
    }

    @RequestMapping(value = "/boundedtransports/{id}", method = RequestMethod.GET)
    public ModelAndView listBoundedTransports(@PathVariable("id") int id)
    {
        TransportModel transportmodel = transportModelService.getTransportModelById(id);
        List<Transport> transports = transportmodel.getTransports();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transports/transports");
        modelAndView.addObject("transports", transports);
        return modelAndView;
    }

    @RequestMapping(value = "/transportmodels/add", method = RequestMethod.GET)
    public ModelAndView addTransportModel()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Engine> engines = engineService.getAllEngines();
        modelAndView.addObject("engines", engines);
        modelAndView.setViewName("transportmodels/editortransportmodels");
        return modelAndView;
    }

    @RequestMapping(value = "/transportmodels/add", method = RequestMethod.POST)
    public ModelAndView addTransportModel(@ModelAttribute("transportmodel") TransportModel transportmodel, @RequestParam("engineid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        transportmodel.addEngine(engineService.getEngineById(id));
        modelAndView.setViewName("redirect:/transportmodels");
        transportModelService.addTransportModel(transportmodel);
        return modelAndView;
    }

    @RequestMapping(value = "/transportmodels/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editTransportModel(@PathVariable("id") int id)
    {
        TransportModel transportModel = transportModelService.getTransportModelById(id);
        List<Engine> engines = engineService.getAllEngines();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transportmodels/editortransportmodels");
        modelAndView.addObject("transportmodel",transportModel);
        modelAndView.addObject("engines", engines);
        return modelAndView;
    }

    @RequestMapping(value = "/transportmodels/edit", method = RequestMethod.POST)
    public ModelAndView editTransportModel(@ModelAttribute("transportmodel") TransportModel transportModel, @RequestParam("engineid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        transportModel.addEngine(engineService.getEngineById(id));
        modelAndView.setViewName("redirect:/transportmodels");
        transportModelService.editTransportModel(transportModel);
        return modelAndView;
    }

    @RequestMapping(value = "/transportmodels/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTransportModel(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transportmodels");
        TransportModel transportModel = transportModelService.getTransportModelById(id);
        transportModelService.deleteTransportModel(transportModel);
        return modelAndView;
    }

}
