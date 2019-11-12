package transportsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import transportsystem.model.Transport;
import transportsystem.model.TransportModel;
import transportsystem.service.TransportService;
import java.util.*;

@Controller
public class TestController {
    private TransportService transportService = new TransportService();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView startPage() {
        List<Transport> transports = transportService.getAllTransports();
        List<TransportModel> transportmodels = transportService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("transports",transports);
        modelAndView.addObject("transportmodels",transportmodels);
        return modelAndView;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView aboutPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") Integer id)
    {
        Transport transport = transportService.getTransportById(id);
        List<TransportModel> transportmodels = transportService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("transport",transport);
        modelAndView.addObject("transportmodels", transportmodels);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editTransport(@ModelAttribute("transport") Transport transport)
    {
        ModelAndView modelAndView = new ModelAndView();
        List<TransportModel> transportmodels = transportService.getAllTransportModels();
        modelAndView.setViewName("redirect:/");
        transportService.edit(transport);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage()
    {
        List<TransportModel> transportmodels = transportService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("transportmodels", transportmodels);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addTransport(@ModelAttribute("transport") Transport transport)
    {
        ModelAndView modelAndView = new ModelAndView();
        List<TransportModel> transportmodels = transportService.getAllTransportModels();
        modelAndView.setViewName("redirect:/");
        transportService.add(transport);
        return modelAndView;
    }
}