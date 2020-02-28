package com.transportsystem.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transportsystem.backend.embeddable.Comment;
import com.transportsystem.backend.model.Employee;
import com.transportsystem.backend.model.Transport;
import com.transportsystem.backend.model.TransportOrder;
import com.transportsystem.backend.service.EmployeeService;
import com.transportsystem.backend.service.JsonService;
import com.transportsystem.backend.service.TransportOrderService;
import com.transportsystem.backend.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
@CrossOrigin(origins = "*")
public class TransportOrderRESTController {
    private TransportOrderService transportOrderService;
    private EmployeeService employeeService;
    private TransportService transportService;
    private JsonService jsonService;

    @Autowired
    public void setTransportOrderService(TransportOrderService transportOrderService) {
        this.transportOrderService = transportOrderService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setTransportService(TransportService transportService) {
        this.transportService = transportService;
    }

    @Autowired
    public void setJsonService(JsonService jsonService) {this.jsonService = jsonService;}

    @GetMapping("/transportorders")
    public List<TransportOrder> readAllTransportOrders() {
        List<TransportOrder> transportOrders = transportOrderService.getAllTransportOrders();
        return transportOrders;
    }

    @GetMapping("/transportorders/{id}")
    public TransportOrder readTransportOrder(@PathVariable int id) {
        TransportOrder transportOrder = transportOrderService.getTransportOrderById(id);
        return transportOrder;
    }

    @PostMapping("/transportorders/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public TransportOrder createTransportOrder(@RequestBody String data) throws JsonProcessingException {
        Employee employeeresponsible = employeeService.getEmployeeById(jsonService.getValueFromJson(data,"responsibleemployeeid").asInt());
        Employee employeecustomer = employeeService.getEmployeeById(jsonService.getValueFromJson(data,"customeremployeeid").asInt());
        Transport transport = transportService.getTransportById(jsonService.getValueFromJson(data,"transportid").asInt());
        TransportOrder transportOrder = jsonService.getObjectMapper().readValue(data,TransportOrder.class);
        String comment = jsonService.getCommentFromJson(data);
        // TODO: instance initializer in TransportOrder is necessary. Rework?
        transportOrder.getComments().addComment(comment);
        transportOrder.addEmployeeresponsible(employeeresponsible);
        transportOrder.addEmployeecustomer(employeecustomer);
        transportOrder.addTransport(transport);
        // TODO: WHY THIS METHOD?
        transportOrder.addOrdernumber();
        transportOrderService.addTransportOrder(transportOrder);
        return transportOrder;
    }

    @PutMapping("/transportorders/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public TransportOrder updateTransportOrder(@RequestBody String data) throws JsonProcessingException {
        Employee employeeresponsible = employeeService.getEmployeeById(jsonService.getValueFromJson(data,"employeeresponsibleid").asInt());
        Employee employeecustomer = employeeService.getEmployeeById(jsonService.getValueFromJson(data,"employeecustomerid").asInt());
        Transport transport = transportService.getTransportById(jsonService.getValueFromJson(data,"transportid").asInt());
        TransportOrder transportOrder = jsonService.getObjectMapper().readValue(data,TransportOrder.class);
        String comment = jsonService.getCommentFromJson(data);
        transportOrder.addEmployeeresponsible(employeeresponsible);
        transportOrder.addEmployeecustomer(employeecustomer);
        transportOrder.addTransport(transport);
        Comment comments = transportOrderService.getTransportOrderById(jsonService.getValueFromJson(data,"transportorderid").asInt()).getComments();
        comments.addComment(comment);
        transportOrder.setComments(comments);
        transportOrderService.editTransportOrder(transportOrder);
        return transportOrder;
    }

    @DeleteMapping("/transportorders/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTransportOrder(@PathVariable int id) {
        TransportOrder transportOrder = transportOrderService.getTransportOrderById(id);
        transportOrderService.deleteTransportOrder(transportOrder);
    }
}
