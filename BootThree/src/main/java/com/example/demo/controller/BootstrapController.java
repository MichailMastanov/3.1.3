package com.example.demo.controller;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class BootstrapController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepositories(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @GetMapping("/admin/index")
    public String usersAdminAll(){
        return "fetch_admin";
    }

    @GetMapping("/userOneActive")
    public String userOneActive(){
        return "fetch_user";
    }

}


