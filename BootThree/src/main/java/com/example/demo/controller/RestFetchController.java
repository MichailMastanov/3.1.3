package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
public class RestFetchController {

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

    @RequestMapping(value = "/admin/indexRest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<java.util.List<User>> usersAdminList(){
        List<User> usersAll = userRepository.findAll();
        return new ResponseEntity<>(usersAll, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/deleteById/{id}",  method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Optional> deleteById(@PathVariable("id") Long id){
        Optional<User> user = userRepository.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/deleteResult/{id}",  method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteResult(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/createNewUs",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createNewUser(@RequestBody User user){
        Set<Role> roleSet = new HashSet<>();
        for (String role : user.getRolesArray()) {
            roleSet.add(roleRepository.findByName(role.toLowerCase()));
        }
        User userStart = new User(user.getUsername(), user.getPassword());
        userStart.setRole(roleSet);
        userRepository.save(userStart);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/updateUserStart/{id}",  method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Optional> updateUserStart(@PathVariable("id") Long id){
        Optional<User> user = userRepository.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/updateResult",  method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<java.util.List<User>> updateResult(@RequestBody User user){
        userRepository.save(user);
        List<User> usersAll = userRepository.findAll();
        return new ResponseEntity<>(usersAll, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/activeUser",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> activUser(Principal principal){
        User userName = (User)userRepository.findByUsername(principal.getName());
        return new ResponseEntity<>(userName, HttpStatus.OK);
    }

    @RequestMapping(value = "/userActive",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> userActive(Principal principal){
        User userName = (User)userRepository.findByUsername(principal.getName());
        return new ResponseEntity<>(userName, HttpStatus.OK);
    }

}
