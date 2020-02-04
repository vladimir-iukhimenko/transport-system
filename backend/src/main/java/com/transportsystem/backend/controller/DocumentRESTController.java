package com.transportsystem.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transportsystem.backend.model.*;
import com.transportsystem.backend.service.DocumentService;
import com.transportsystem.backend.service.EmployeeService;
import com.transportsystem.backend.service.JsonService;
import com.transportsystem.backend.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api", produces = "application/json")
@CrossOrigin(origins = "*")
public class DocumentRESTController {

    private DocumentService documentService;
    private TransportService transportService;
    private EmployeeService employeeService;
    private JsonService jsonService;

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Autowired
    public void setTransportService(TransportService transportService) {this.transportService = transportService;}

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {this.employeeService = employeeService;}

    @Autowired
    public void setJsonService(JsonService jsonService) {this.jsonService = jsonService;}

    @GetMapping("/transportdocs")
    public List<Document> readAllDocumentTransports() {
        List<Document> listDocumentTransports = documentService.getAllTransportDocuments();
        return listDocumentTransports;
    }

    @GetMapping("/employeedocs")
    public List<Document> readAllDocumentEmployees() {
        List<Document> listDocumentEmployees = documentService.getAllEmployeeDocuments();
        return listDocumentEmployees;
    }

    @GetMapping("/employess/boundeddocuments/{id}")
    public List<DocumentEmployee> readAllBoundedDocumentEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        List<DocumentEmployee> employeedocuments = employee.getEmployeedocuments();
        return employeedocuments;
    }

    @GetMapping("documents/boundeddocuments/{id}")
    public List<DocumentTransport> readAllBoundedDocumentTransport(@PathVariable int id) {
        Transport transport = transportService.getTransportById(id);
        List<DocumentTransport> transportdocuments = transport.getTransportdocuments();
        return transportdocuments;
    }

    @PostMapping("transportdocs/add")
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentTransport createDocumentTransport(@RequestBody String data) throws JsonProcessingException {
        Integer transportid = jsonService.getValueFromJson(data,"transportid").asInt();
        Transport transport = transportService.getTransportById(transportid);
        DocumentTransport documentTransport = jsonService.getObjectMapper().readValue(data,DocumentTransport.class);
        documentTransport.addTransport(transport);
        documentService.add(documentTransport);
        return documentTransport;
    }

    @PostMapping("employeedocs/add")
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentEmployee createDocumentEmployee(@RequestBody String data) throws JsonProcessingException {
        Integer employeeid = jsonService.getValueFromJson(data,"employeeid").asInt();
        Employee employee = employeeService.getEmployeeById(employeeid);
        DocumentEmployee documentEmployee = jsonService.getObjectMapper().readValue(data,DocumentEmployee.class);
        documentEmployee.addEmployee(employee);
        documentService.add(documentEmployee);
        return documentEmployee;
    }

    @PutMapping("transportdocs/edit")
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentTransport updateDocumentTransport(@RequestBody String data) throws JsonProcessingException {
        Integer transportid = jsonService.getValueFromJson(data,"transportid").asInt();
        Transport transport = transportService.getTransportById(transportid);
        DocumentTransport documentTransport = jsonService.getObjectMapper().readValue(data,DocumentTransport.class);
        documentTransport.addTransport(transport);
        documentService.edit(documentTransport);
        return documentTransport;
    }

    @PutMapping("employeedocs/edit")
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentEmployee updateDocumentEmployee(@RequestBody String data) throws JsonProcessingException {
        Integer employeeid = jsonService.getValueFromJson(data,"employeeid").asInt();
        Employee employee = employeeService.getEmployeeById(employeeid);
        DocumentEmployee documentEmployee = jsonService.getObjectMapper().readValue(data,DocumentEmployee.class);
        documentEmployee.addEmployee(employee);
        documentService.edit(documentEmployee);
        return documentEmployee;
    }

    @DeleteMapping("documents/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDocumentTransport(@PathVariable int id) {
        Document document = documentService.getDocumentTransportById(id);
        documentService.delete(document);
    }
}
