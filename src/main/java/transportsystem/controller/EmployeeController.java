package transportsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import transportsystem.model.Employee;
import transportsystem.service.EmployeeService;
import java.util.List;

/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Controller
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {this.employeeService = employeeService;}

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ModelAndView listEmployees() {
        List<Employee> listEmployees = employeeService.getAllEmployees();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employees");
        modelAndView.addObject("employees",listEmployees);
        return modelAndView;
    }

    @RequestMapping(value = "/employees/add", method = RequestMethod.GET)
    public ModelAndView addEmployee() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editoremployees");
        return modelAndView;
    }

    @RequestMapping(value = "/employees/add", method = RequestMethod.POST)
    public ModelAndView addEmployee(@ModelAttribute("employee") Employee employee)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employees");
        employeeService.add(employee);
        return modelAndView;
    }

    @RequestMapping(value = "/employees/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editEmployee(@PathVariable("id") int id)
    {
        Employee employee = employeeService.getEmployeeById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editoremployees");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @RequestMapping(value = "/employees/edit", method = RequestMethod.POST)
    public ModelAndView editEmployee(@ModelAttribute("employee") Employee employee)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employees");
        employeeService.edit(employee);
        return modelAndView;
    }

    @RequestMapping(value = "/employees/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employees");
        Employee employee = employeeService.getEmployeeById(id);
        employeeService.delete(employee);
        return modelAndView;
    }
}
