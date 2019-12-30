package com.transportsystem.backend.controller;

/**
 * Created by DZCKJS0 on 18.11.2019.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.transportsystem.backend.model.*;
import com.transportsystem.backend.service.DocumentService;
import com.transportsystem.backend.service.EmployeeService;
import com.transportsystem.backend.service.TransportService;

import java.util.*;

@Controller
public class DocumentController {
    private DocumentService documentService;
    private TransportService transportService;
    private EmployeeService employeeService;

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Autowired
    public void setTransportService(TransportService transportService) {this.transportService = transportService;}

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {this.employeeService = employeeService;}

    public ModelAndView listDocuments(List<Document> documents,String type)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("documents/documents");
        modelAndView.addObject("documents", documents);
        modelAndView.addObject("type",type);
        return modelAndView;
    }

    @RequestMapping(value = "/transportdocs", method = RequestMethod.GET)
    public ModelAndView listDocumentTransports() {
        List<Document> transportdocuments = documentService.getAllTransportDocuments();
        return listDocuments(transportdocuments,"DocumentTransport");
    }

    @RequestMapping(value = "/employeedocs", method = RequestMethod.GET)
    public ModelAndView listDocumentEmployees() {
        List<Document> employeedocuments = documentService.getAllEmployeeDocuments();
        return listDocuments(employeedocuments, "DocumentEmployee");
    }

    @RequestMapping(value = "/employees/boundeddocuments/{id}", method = RequestMethod.GET)
    public ModelAndView listBoundedEmployeeDocuments(@PathVariable("id") int id)
    {
        Employee employee = employeeService.getEmployeeById(id);
        List<DocumentEmployee> documents = employee.getEmployeedocuments();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("documents/documents");
        modelAndView.addObject("documents", documents);
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @RequestMapping(value = "/transportdocs/add", method = RequestMethod.GET)
    public ModelAndView addDocumentTransport()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Transport> transports = transportService.getAllTransports();
        modelAndView.setViewName("documents/editordocuments");
        modelAndView.addObject("type","DocumentTransport");
        modelAndView.addObject("transports",transports);
        return modelAndView;
    }

    @RequestMapping(value = "/transportdocs/add", method = RequestMethod.POST)
    public ModelAndView addDocumentTransport(@ModelAttribute("document") DocumentTransport document, @RequestParam("transportid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        Transport transport = transportService.getTransportById(id);
        document.addTransport(transport);
        modelAndView.setViewName("redirect:/transportdocs");
        documentService.add(document);
        return modelAndView;
    }

    @RequestMapping(value = "/employeedocs/add", method = RequestMethod.GET)
    public ModelAndView addDocumentEmployee()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Employee> employees = employeeService.getAllEmployees();
        modelAndView.setViewName("documents/editordocuments");
        modelAndView.addObject("type","DocumentEmployee");
        modelAndView.addObject("employees",employees);
        return modelAndView;
    }

    @RequestMapping(value = "/employeedocs/add", method = RequestMethod.POST)
    public ModelAndView addDocumentEmployee(@ModelAttribute("document") DocumentEmployee document, @RequestParam("employeeid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        Employee employee = employeeService.getEmployeeById(id);
        document.addEmployee(employee);
        modelAndView.setViewName("redirect:/employeedocs");
        documentService.add(document);
        return modelAndView;
    }

    @RequestMapping(value = "/transportdocs/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editDocumentTransport(@PathVariable("id") Integer id)
    {
        Document document = documentService.getDocumentTransportById(id);
        List<Transport> transports = transportService.getAllTransports();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("documents/editordocuments");
        modelAndView.addObject("document",document);
        modelAndView.addObject("type","DocumentTransport");
        modelAndView.addObject("transports",transports);
        return modelAndView;
    }

    @RequestMapping(value = "/transportdocs/edit", method = RequestMethod.POST)
    public ModelAndView editDocumentTransport(@ModelAttribute("document") DocumentTransport document, @RequestParam("transportid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        Transport transport = transportService.getTransportById(id);
        document.addTransport(transport);
        modelAndView.setViewName("redirect:/transportdocs");
        documentService.edit(document);
        return modelAndView;
    }

    @RequestMapping(value = "/employeedocs/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editDocumentEmployee(@PathVariable("id") Integer id)
    {
        Document document = documentService.getDocumentEmployeeById(id);
        List<Employee> employees = employeeService.getAllEmployees();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("documents/editordocuments");
        modelAndView.addObject("document",document);
        modelAndView.addObject("type","DocumentEmployee");
        modelAndView.addObject("employees",employees);
        return modelAndView;
    }

    @RequestMapping(value = "/employeedocs/edit", method = RequestMethod.POST)
    public ModelAndView editDocumentEmployee(@ModelAttribute("document") DocumentEmployee document, @RequestParam("employeeid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        Employee employee = employeeService.getEmployeeById(id);
        document.addEmployee(employee);
        modelAndView.setViewName("redirect:/employeedocs");
        documentService.edit(document);
        return modelAndView;
    }

    @RequestMapping(value = "/transportdocs/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteDocumentTransport(@PathVariable("id") Integer id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transportdocs");
        Document document = documentService.getDocumentTransportById(id);
        documentService.delete(document);
        return modelAndView;
    }

    @RequestMapping(value = "/employeedocs/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteDocumentEmployee(@PathVariable("id") Integer id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employeedocs");
        Document document = documentService.getDocumentEmployeeById(id);
        documentService.delete(document);
        return modelAndView;
    }
}
