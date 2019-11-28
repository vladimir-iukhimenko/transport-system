package transportsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import transportsystem.model.Transport;
import transportsystem.model.TransportModel;
import transportsystem.service.TransportModelService;
import transportsystem.service.TransportService;
import java.util.*;

@Controller
public class TransportController {

    private TransportService transportService;
    private TransportModelService transportModelService;

    @Autowired
    public void setTransportService(TransportService transportservice)
    {
        this.transportService = transportservice;
    }

    @Autowired
    public void setTransportModelService(TransportModelService transportModelService) {this.transportModelService = transportModelService;}

    @RequestMapping(value="/",method = RequestMethod.GET)
    public ModelAndView startpage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/transports", method = RequestMethod.GET)
    public ModelAndView listTransports() {
        List<Transport> transports = transportService.getAllTransports();
        List<TransportModel> transportmodels = transportModelService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transports");
        modelAndView.addObject("transports",transports);
        modelAndView.addObject("transportmodels",transportmodels);
        return modelAndView;
    }



    @RequestMapping(value = "/transports/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editTransport(@PathVariable("id") Integer id)
    {
        Transport transport = transportService.getTransportById(id);
        List<TransportModel> transportmodels = transportModelService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editortransports");
        modelAndView.addObject("transport",transport);
        modelAndView.addObject("transportmodels", transportmodels);
        return modelAndView;
    }

    @RequestMapping(value = "/transport/edit", method = RequestMethod.POST)
    public ModelAndView editTransport(@ModelAttribute("transport") Transport transport, @RequestParam("transportmodelid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        transport.setTransportmodel(transportModelService.getTransportModelById(id));
        modelAndView.setViewName("redirect:/transports");
        transportService.editTransport(transport);
        return modelAndView;
    }

    @RequestMapping(value = "/transport/add", method = RequestMethod.GET)
    public ModelAndView addTransport()
    {
        List<TransportModel> transportmodels = transportModelService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editortransports");
        modelAndView.addObject("transportmodels", transportmodels);
        return modelAndView;
    }

    @RequestMapping(value = "/transport/add", method = RequestMethod.POST)
    public ModelAndView addTransport(@ModelAttribute("transport") Transport transport,@RequestParam("transportmodelid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        transport.setTransportmodel(transportModelService.getTransportModelById(id));
        modelAndView.setViewName("redirect:/transports");
        transportService.addTransport(transport);
        return modelAndView;
    }

    @RequestMapping(value = "/transport/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTransport(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transports");
        Transport transport = transportService.getTransportById(id);
        transportService.deleteTransport(transport);
        return modelAndView;
    }
}