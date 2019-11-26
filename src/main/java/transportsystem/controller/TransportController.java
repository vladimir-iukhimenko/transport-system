package transportsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import transportsystem.model.Engine;
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

    @RequestMapping(value = "/engines", method = RequestMethod.GET)
    public ModelAndView listEngines() {
        List<Engine> engines = transportService.getAllEngines();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("engines");
        modelAndView.addObject("engines", engines);
        return modelAndView;
    }

    @RequestMapping(value = "/edittransport/{id}", method = RequestMethod.GET)
    public ModelAndView editTransport(@PathVariable("id") Integer id)
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
        transportService.editTransport(transport);
        return modelAndView;
    }

    @RequestMapping(value = "/addtransport", method = RequestMethod.GET)
    public ModelAndView addTransport()
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
        transportService.addTransport(transport);
        return modelAndView;
    }

    @RequestMapping(value = "/deletetransport/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTransport(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transports");
        Transport transport = transportService.getTransportById(id);
        transportService.deleteTransport(transport);
        return modelAndView;
    }

    @RequestMapping(value = "/addtransportmodel", method = RequestMethod.GET)
    public ModelAndView addTransportModel()
    {
        ModelAndView modelAndView = new ModelAndView();
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
        transportService.editTransporModel(transportModel);
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

    @RequestMapping(value = "/addengine", method = RequestMethod.GET)
    public ModelAndView addEngine()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editorengines");
        return modelAndView;
    }

    @RequestMapping(value = "/addengine", method = RequestMethod.POST)
    public ModelAndView addEngine(@ModelAttribute("engine") Engine engine)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/engines");
        transportService.addEngine(engine);
        return modelAndView;
    }

    @RequestMapping(value = "/editengine/{id}", method = RequestMethod.GET)
    public ModelAndView editEngine(@PathVariable("id") int id)
    {
        Engine engine = transportService.getEngineById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/editorengines");
        modelAndView.addObject("engine", engine);
        return modelAndView;
    }

    @RequestMapping(value = "/editengine", method = RequestMethod.POST)
    public ModelAndView editEngine(@ModelAttribute("engine") Engine engine)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/engines");
        transportService.editEngine(engine);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteengine/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEngine(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/engines");
        Engine engine = transportService.getEngineById(id);
        transportService.deleteEngine(engine);
        return modelAndView;
    }
}