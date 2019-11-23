package transportsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import transportsystem.model.Transport;
import transportsystem.model.TransportModel;
import transportsystem.service.TransportService;
import java.util.*;

@Controller
public class TransportController {

    private TransportService transportService;

    @Autowired
    public void setTransportService(TransportService transportservice)
    {
        this.transportService = transportservice;
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public ModelAndView startpage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/transports", method = RequestMethod.GET)
    public ModelAndView listTransports() {
        List<Transport> transports = transportService.getAllTransports();
        List<TransportModel> transportmodels = transportService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transports");
        modelAndView.addObject("transports",transports);
        modelAndView.addObject("transportmodels",transportmodels);
        return modelAndView;
    }

    @RequestMapping(value = "/transportmodels", method = RequestMethod.GET)
    public ModelAndView listTransportModels() {
        List<TransportModel> transportmodels = transportService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transportmodels");
        modelAndView.addObject("transportmodels",transportmodels);
        return modelAndView;
    }

    @RequestMapping(value = "/boundedtransports/{id}", method = RequestMethod.GET)
    public ModelAndView listBoundedTransports(@PathVariable("id") Integer id)
    {
        TransportModel transportmodel = transportService.getTransportModelById(id);
        List<Transport> transports = transportmodel.getTransports();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transports");
        modelAndView.addObject("transports", transports);
        return modelAndView;
    }

    @RequestMapping(value = "/edittransport/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") Integer id)
    {
        Transport transport = transportService.getTransportById(id);
        List<TransportModel> transportmodels = transportService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editortransports");
        modelAndView.addObject("transport",transport);
        modelAndView.addObject("transportmodels", transportmodels);
        return modelAndView;
    }

    @RequestMapping(value = "/edittransport", method = RequestMethod.POST)
    public ModelAndView editTransport(@ModelAttribute("transport") Transport transport, @RequestParam("transportmodelid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        transport.setTransportmodel(transportService.getTransportModelById(id));
        modelAndView.setViewName("redirect:/transports");
        transportService.edit(transport);
        return modelAndView;
    }

    @RequestMapping(value = "/addtransport", method = RequestMethod.GET)
    public ModelAndView addPage()
    {
        List<TransportModel> transportmodels = transportService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editortransports");
        modelAndView.addObject("transportmodels", transportmodels);
        return modelAndView;
    }

    @RequestMapping(value = "/addtransport", method = RequestMethod.POST)
    public ModelAndView addTransport(@ModelAttribute("transport") Transport transport,@RequestParam("transportmodelid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        transport.setTransportmodel(transportService.getTransportModelById(id));
        modelAndView.setViewName("redirect:/transports");
        transportService.add(transport);
        return modelAndView;
    }

    @RequestMapping(value = "/deletetransport/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTransport(@PathVariable("id") Integer id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transports");
        Transport transport = transportService.getTransportById(id);
        transportService.delete(transport);
        return modelAndView;
    }
}