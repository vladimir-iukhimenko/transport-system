package transportsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import transportsystem.model.Engine;
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


    @RequestMapping(value = "/engines", method = RequestMethod.GET)
    public ModelAndView listEngines() {
        List<Engine> engines = transportService.getAllEngines();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("engines");
        modelAndView.addObject("engines", engines);
        return modelAndView;
    }

    @RequestMapping(value = "/boundedtransportmodels/{id}", method = RequestMethod.GET)
    public ModelAndView listBoundedTransportModels(@PathVariable("id") int id)
    {
        Engine engine = transportService.getEngineById(id);
        List<TransportModel> transportModels = engine.getTransportmodels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transportmodels");
        modelAndView.addObject("transportmodels", transportModels);
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