package com.transportsystem.backend.service.security;

import com.transportsystem.backend.dao.security.RoleDAO;
import com.transportsystem.backend.model.security.ERole;
import com.transportsystem.backend.model.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    private RoleDAO roleDAO;

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) { this.roleDAO = roleDAO; }

    @Transactional
    public Role getRoleByName(ERole name) {return roleDAO.getRoleByName(name);}

}
