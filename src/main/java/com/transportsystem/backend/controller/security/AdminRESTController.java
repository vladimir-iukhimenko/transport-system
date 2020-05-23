package com.transportsystem.backend.controller.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transportsystem.backend.model.Employee;
import com.transportsystem.backend.model.security.User;
import com.transportsystem.backend.service.EmployeeService;
import com.transportsystem.backend.service.JsonService;
import com.transportsystem.backend.service.security.RoleService;
import com.transportsystem.backend.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/", produces = "application/json")
public class AdminRESTController {
    private UserService userService;
    private JsonService jsonService;
    private EmployeeService employeeService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setJsonService(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) { this.roleService = roleService; }

    @PutMapping(path = "api/admin/edit", consumes = "application/json")
    public User updateUser(@RequestBody String data) throws JsonProcessingException {
        User user = userService.loadUserByUsername(jsonService.getValueFromJson(data, "username").asText());
        Set<String> stringRoles = new HashSet<>();
        jsonService.getValueFromJson(data,"roles").iterator().forEachRemaining((str) -> stringRoles.add(str.asText()));
        user.setRoles(roleService.convertToRolesSet(stringRoles));
        user.setEnabled(jsonService.getValueFromJson(data,"enabled").asBoolean());
        Employee employee = employeeService.getEmployeeById(jsonService.getValueFromJson(data,"employeeid").asInt());
        user.setEmployee(employee);

        userService.editUser(user);
        return user;
    }

    @DeleteMapping("api/admin/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        userService.deleteUser(user);
    }

}
