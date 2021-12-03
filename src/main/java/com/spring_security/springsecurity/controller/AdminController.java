package com.spring_security.springsecurity.controller;

import com.spring_security.springsecurity.entity.Role;
import com.spring_security.springsecurity.entity.SimpleMessage;
import com.spring_security.springsecurity.entity.User;
import com.spring_security.springsecurity.exception.MultipleUserException;
import com.spring_security.springsecurity.exception.UserNotFoundException;
import com.spring_security.springsecurity.repository.RoleRepository;
import com.spring_security.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;

    public static final String emp_role = "ROLE_EMPLOYEE";

    @PostMapping("/create-employee")
    public ResponseEntity<User> createEmployee(@RequestBody User user) throws MultipleUserException {

        user.setRole(roleRepository.findByRoleName(emp_role));
        User fetchedUser = userService.userCreation(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc","EMPLOYEE CREATED SUCCESSFULLY");
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(fetchedUser);
    }

    @GetMapping("/users-list")
    public ResponseEntity<List<User>> getAllEmployeeList(@RequestParam String list){
        if(list == ""){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if(list.equals("employees")){
            List<User> userList = userService.getUsers("employees");
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        }else if(list.equals("users")){
            // get all users list
            List<User> userList = userService.getUsers("users");
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        }else if(list.equals("all")){
            // get all users_tbl data including employees
            List<User> userList = userService.getUsers("all");
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/change-role")
    public ResponseEntity<String> changeUserEntityRole(@RequestBody User user){

      //  String message = userService.changeRoleOfUser(user);
        return null;
    }
    @DeleteMapping("/delete-employee/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String empId) throws UserNotFoundException {
        String successMessage = userService.deleteUser(Integer.parseInt(empId));
        if(successMessage != "" || successMessage != null) return ResponseEntity.status(HttpStatus.OK).body(new SimpleMessage(successMessage));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/user-is-locked/{uid}")
    public ResponseEntity<?> updateUserData(@PathVariable int uid,@RequestBody User user) throws UserNotFoundException {
        User updatedUser = userService.updateUser(uid,user);

        return ResponseEntity.status(HttpStatus.OK).body(new SimpleMessage("User updated Successfully"));
    }
}
