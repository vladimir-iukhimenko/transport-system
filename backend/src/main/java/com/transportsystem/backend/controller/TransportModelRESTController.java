package com.transportsystem.backend.controller;

import com.transportsystem.backend.model.TransportModel;
import com.transportsystem.backend.service.TransportModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class TransportModelRESTController {
    private TransportModelService transportModelService;

    @Autowired
    public void setTransportModelService(TransportModelService transportModelService) {
        this.transportModelService = transportModelService;
    }

    @GetMapping("/transportmodels")
    public List<TransportModel> listTransportModels() {
        List<TransportModel> transportmodels = transportModelService.getAllTransportModels();
        return transportmodels;
    }

}
