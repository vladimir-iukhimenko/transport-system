package com.transportsystem.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.transportsystem.backend.model.Transport;
import com.transportsystem.backend.model.TransportModel;
import com.transportsystem.backend.service.TransportModelService;
import com.transportsystem.backend.service.TransportService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transports")
public class TransportRESTController {

    private TransportService transportService;
    private TransportModelService transportModelService;

    @Autowired
    public void setTransportService(TransportService transportservice)
    {
        this.transportService = transportservice;
    }

    @Autowired
    public void setTransportModelService(TransportModelService transportModelService) {this.transportModelService = transportModelService;}

    //@RequestMapping(value="/",method = RequestMethod.GET)
    //public ModelAndView startpage() {
       // ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("index");
        //return modelAndView;
    //}

    @RequestMapping(value = "/", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Transport> listTransports() {
        List<Transport> transports = transportService.getAllTransports();
        return transports;
    }



    @RequestMapping(value = "/transports/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editTransport(@PathVariable("id") Integer id)
    {
        Transport transport = transportService.getTransportById(id);
        List<TransportModel> transportmodels = transportModelService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transports/editortransports");
        modelAndView.addObject("transport",transport);
        modelAndView.addObject("transportmodels", transportmodels);
        return modelAndView;
    }

    @RequestMapping(value = "/transports/edit", method = RequestMethod.POST)
    public ModelAndView editTransport(@ModelAttribute("transport") Transport transport, @RequestParam("transportmodelid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        transport.addTransportmodel(transportModelService.getTransportModelById(id));
        modelAndView.setViewName("redirect:/transports");
        transportService.editTransport(transport);
        return modelAndView;
    }

    @RequestMapping(value = "/transports/add", method = RequestMethod.GET)
    public ModelAndView addTransport()
    {
        List<TransportModel> transportmodels = transportModelService.getAllTransportModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transports/editortransports");
        modelAndView.addObject("transportmodels", transportmodels);
        return modelAndView;
    }

    @RequestMapping(value = "/transports/add", method = RequestMethod.POST)
    public ModelAndView addTransport(@Valid @ModelAttribute("transport") Transport transport, Errors errors, @RequestParam("transportmodelid") int id)
    {
        if (errors.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            List<ObjectError> errorslist = errors.getAllErrors();
            for (ObjectError oe: errorslist) System.out.println(oe.getDefaultMessage());
            modelAndView.addObject("errors", errors);
            modelAndView.setViewName("transports/editortransports");
            List<TransportModel> transportmodels = transportModelService.getAllTransportModels();
            modelAndView.addObject("transportmodels", transportmodels);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView();
        transport.addTransportmodel(transportModelService.getTransportModelById(id));
        modelAndView.setViewName("redirect:/transports");
        transportService.addTransport(transport);
        return modelAndView;
    }

    @RequestMapping(value = "/transports/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTransport(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transports");
        Transport transport = transportService.getTransportById(id);
        transportService.deleteTransport(transport);
        return modelAndView;
    }
}