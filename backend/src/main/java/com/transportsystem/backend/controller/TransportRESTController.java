package com.transportsystem.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transportsystem.backend.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.transportsystem.backend.model.Transport;
import com.transportsystem.backend.service.TransportModelService;
import com.transportsystem.backend.service.TransportService;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
@CrossOrigin(origins = "*")
public class TransportRESTController {

    private TransportService transportService;
    private TransportModelService transportModelService;
    private JsonService jsonService;

    @Autowired
    public void setTransportService(TransportService transportservice)
    {
        this.transportService = transportservice;
    }

    @Autowired
    public void setTransportModelService(TransportModelService transportModelService) {this.transportModelService = transportModelService;}

    @Autowired
    public void setJsonService(JsonService jsonService) {this.jsonService = jsonService;}

    @GetMapping("/transports")
    public List<Transport> readAllTransports() {
        List<Transport> transports = transportService.getAllTransports();
        return transports;
    }

    @GetMapping("/transports/{id}")
    public Transport readTransport(@PathVariable int id) {
        Transport transport = transportService.getTransportById(id);
        return transport;
    }

    @PostMapping(value = "/transports/add",consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Transport createTransport(@RequestBody String data) throws JsonProcessingException {
        Transport transport = jsonService.getObjectMapper().readValue(data,Transport.class);
        Integer transportmodelid = jsonService.getValueFromJson(data,"transportmodelid").asInt();
        transport.addTransportmodel(transportModelService.getTransportModelById(transportmodelid));
        transportService.addTransport(transport);
        return transport;
    }

    @PutMapping(value = "/transports/edit", consumes = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public Transport updateTransport(@RequestBody String data) throws JsonProcessingException {
        Transport transport = jsonService.getObjectMapper().readValue(data,Transport.class);
        Integer transportmodelid = jsonService.getValueFromJson(data,"transportmodelid").asInt();
        transport.addTransportmodel(transportModelService.getTransportModelById(transportmodelid));
        transportService.editTransport(transport);
        return transport;
    }

    @DeleteMapping("/transports/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTransport(@PathVariable int id) {
        Transport transport = transportService.getTransportById(id);
        transportService.deleteTransport(transport);
    }
}
