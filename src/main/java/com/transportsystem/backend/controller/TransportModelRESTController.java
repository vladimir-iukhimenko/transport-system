package com.transportsystem.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transportsystem.backend.model.TransportModel;
import com.transportsystem.backend.service.JsonService;
import com.transportsystem.backend.service.TransportModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
@CrossOrigin(origins = "*")
public class TransportModelRESTController {
    private TransportModelService transportModelService;
    private JsonService jsonService;

    @Autowired
    public void setTransportModelService(TransportModelService transportModelService) {
        this.transportModelService = transportModelService;
    }

    @Autowired
    public void setJsonService(JsonService jsonService) {this.jsonService = jsonService;}

    @GetMapping("/transportmodels")
    public List<TransportModel> readAllTransportModels() {
        List<TransportModel> transportmodels = transportModelService.getAllTransportModels();
        return transportmodels;
    }

    @GetMapping("/transportmodels/{id}")
    public TransportModel readTransportModel(@PathVariable int id) {
        TransportModel transportModel = transportModelService.getTransportModelById(id);
        return transportModel;
    }

    @PostMapping("/transportmodels/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public TransportModel createTransportModel(@RequestBody String data) throws JsonProcessingException {
        TransportModel transportModel = jsonService.getObjectMapper().readValue(data,TransportModel.class);
        transportModelService.addTransportModel(transportModel);
        return transportModel;
    }

    @PutMapping("/transportmodels/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public TransportModel updateTransportModel(@RequestBody String data) throws JsonProcessingException {
        TransportModel transportModel = jsonService.getObjectMapper().readValue(data,TransportModel.class);
        transportModelService.editTransportModel(transportModel);
        return transportModel;
    }

    @DeleteMapping("/transportmodels/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTransportModel(@PathVariable int id) {
        TransportModel transportModel = transportModelService.getTransportModelById(id);
        transportModelService.deleteTransportModel(transportModel);
    }
}
