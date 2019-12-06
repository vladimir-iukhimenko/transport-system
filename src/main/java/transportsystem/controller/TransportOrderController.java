package transportsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import transportsystem.model.Employee;
import transportsystem.model.Transport;
import transportsystem.model.TransportOrder;
import transportsystem.service.EmployeeService;
import transportsystem.service.TransportOrderService;
import transportsystem.service.TransportService;

import java.util.List;

/**
 * Created by DZCKJS0 on 02.12.2019.
 */
@Controller
public class TransportOrderController {
    private TransportOrderService transportOrderService;
    private EmployeeService employeeService;
    private TransportService transportService;

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

    @RequestMapping(value = "/transportorders", method = RequestMethod.GET)
    public ModelAndView listTransportOrders()
    {
        List<TransportOrder> transportOrders = transportOrderService.getAllTransportOrders();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transportorders/transportorders");
        modelAndView.addObject("transportorders", transportOrders);
        return modelAndView;
    }

    @RequestMapping(value = "/transportorders/add", method = RequestMethod.GET)
    public ModelAndView addTransportOrder()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Employee> employees = employeeService.getAllEmployees();
        List<Transport> transports = transportService.getAllTransports();
        modelAndView.setViewName("transportorders/editortransportorders");
        modelAndView.addObject("employees",employees);
        modelAndView.addObject("transports",transports);
        return modelAndView;
    }

    @RequestMapping(value = "/transportorders/add", method = RequestMethod.POST)
    public ModelAndView addTransportOrder(@ModelAttribute("transportorder") TransportOrder transportOrder,
                                          @RequestParam("employeeresponsible") int employeeresponsibleid,
                                          @RequestParam("employeecustomer") int employeecustomerid,
                                          @RequestParam("transport") int transportid)
    {
        ModelAndView modelAndView = new ModelAndView();
        Employee employeeresponsible = employeeService.getEmployeeById(employeeresponsibleid);
        Employee employeecustomer = employeeService.getEmployeeById(employeecustomerid);
        Transport transport = transportService.getTransportById(transportid);
        transportOrder.addEmployeeresponsible(employeeresponsible);
        transportOrder.addEmployeecustomer(employeecustomer);
        transportOrder.addTransport(transport);
        transportOrder.setOrdernumber();
        modelAndView.setViewName("redirect:/transportorders");
        transportOrderService.addTransportOrder(transportOrder);
        return modelAndView;
    }

    @RequestMapping(value = "/transportorders/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editTransportOrder(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Employee> employees = employeeService.getAllEmployees();
        List<Transport> transports = transportService.getAllTransports();
        TransportOrder transportOrder = transportOrderService.getTransportOrderById(id);
        modelAndView.setViewName("transportorders/editortransportorders");
        modelAndView.addObject("transportorder", transportOrder);
        modelAndView.addObject("employees",employees);
        modelAndView.addObject("transports",transports);
        return modelAndView;
    }

    @RequestMapping(value = "/transportorders/edit", method = RequestMethod.POST)
    public ModelAndView editTransportOrder(@ModelAttribute("transportorder") TransportOrder transportOrder,
                                           @RequestParam("employeeresponsible") int employeeresponsibleid,
                                           @RequestParam("employeecustomer") int employeecustomerid,
                                           @RequestParam("transport") int transportid)
    {
        ModelAndView modelAndView = new ModelAndView();
        Employee employeeresponsible = employeeService.getEmployeeById(employeeresponsibleid);
        Employee employeecustomer = employeeService.getEmployeeById(employeecustomerid);
        Transport transport = transportService.getTransportById(transportid);
        transportOrder.addEmployeeresponsible(employeeresponsible);
        transportOrder.addEmployeecustomer(employeecustomer);
        transportOrder.addTransport(transport);
        modelAndView.setViewName("redirect:/transportorders");
        System.out.println(transportOrder.getOrdernumber());
        transportOrderService.editTransportOrder(transportOrder);
        return modelAndView;
    }

    @RequestMapping(value = "/transportorders/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTransportOrder(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transportorders");
        TransportOrder transportOrder = transportOrderService.getTransportOrderById(id);
        transportOrderService.deleteTransportOrder(transportOrder);
        return modelAndView;
    }
}
