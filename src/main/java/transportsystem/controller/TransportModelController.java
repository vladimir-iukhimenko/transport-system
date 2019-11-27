package transportsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import transportsystem.model.Engine;
import transportsystem.model.Transport;
import transportsystem.model.TransportModel;
import transportsystem.service.TransportModelService;

import java.util.List;

/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Controller
public class TransportModelController {
    private TransportModelService transportModelService;

    @Autowired
    public void setTransportModelService(TransportModelService transportModelService) {
        this.transportModelService = transportModelService;
    }

    @RequestMapping(value = "/transportmodels", method = RequestMethod.GET)
    public ModelAndView listTransportModels() {
        List<TransportModel> transportmodels = transportModelService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transportmodels");
        modelAndView.addObject("transportmodels",transportmodels);
        return modelAndView;
    }

    @RequestMapping(value = "/boundedtransports/{id}", method = RequestMethod.GET)
    public ModelAndView listBoundedTransports(@PathVariable("id") int id)
    {
        TransportModel transportmodel = transportModelService.getTransportModelById(id);
        List<Transport> transports = transportmodel.getTransports();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transports");
        modelAndView.addObject("transports", transports);
        return modelAndView;
    }

    @RequestMapping(value = "/addtransportmodel", method = RequestMethod.GET)
    public ModelAndView addTransportModel()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Engine> engines = transportService.getAllEngines();
        modelAndView.addObject("engines", engines);
        modelAndView.setViewName("editortransportmodels");
        return modelAndView;
    }

    @RequestMapping(value = "/addtransportmodel", method = RequestMethod.POST)
    public ModelAndView addTransportModel(@ModelAttribute("transportmodel") TransportModel transportmodel, @RequestParam("engineid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        transportmodel.setEngine(transportService.getEngineById(id));
        modelAndView.setViewName("redirect:/transportmodels");
        transportService.addTransportModel(transportmodel);
        return modelAndView;
    }

    @RequestMapping(value = "/edittransportmodel/{id}", method = RequestMethod.GET)
    public ModelAndView editTransportModel(@PathVariable("id") int id)
    {
        TransportModel transportModel = transportService.getTransportModelById(id);
        List<Engine> engines = transportService.getAllEngines();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/editortransportmodels");
        modelAndView.addObject("transportmodel",transportModel);
        modelAndView.addObject("engines", engines);
        return modelAndView;
    }

    @RequestMapping(value = "/edittransportmodel", method = RequestMethod.POST)
    public ModelAndView editTransportModel(@ModelAttribute("transportmodel") TransportModel transportModel, @RequestParam("engineid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        transportModel.setEngine(transportService.getEngineById(id));
        modelAndView.setViewName("redirect:/transportmodels");
        transportService.editTransportModel(transportModel);
        return modelAndView;
    }

    @RequestMapping(value = "/deletetransportmodel/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTransportModel(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transportmodels");
        TransportModel transportModel = transportService.getTransportModelById(id);
        transportService.deleteTransportModel(transportModel);
        return modelAndView;
    }

}
