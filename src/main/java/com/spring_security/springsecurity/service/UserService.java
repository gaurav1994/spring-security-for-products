package com.spring_security.springsecurity.service;

import com.spring_security.springsecurity.entity.Role;
import com.spring_security.springsecurity.entity.User;
import com.spring_security.springsecurity.exception.MultipleUserException;
import com.spring_security.springsecurity.exception.UserNotFoundException;
import com.spring_security.springsecurity.repository.RoleRepository;
import com.spring_security.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RoleRepository roleRepository;

    public User userCreation(User user) throws MultipleUserException {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User userFetched= null;
        try {
            userFetched = userRepository.save(user);
        }catch (Exception ex) {
            ex.printStackTrace();
            throw new MultipleUserException("username shouldbe unique");
        }
        return userFetched;
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getUsers(String userFlag){
        if(userFlag.equals("employees")){
            return userRepository.findAllByRoleId(403);
        }
        if(userFlag.equals("users")){
            return userRepository.findAllByRoleId(402);
        }
        if(userFlag.equals("all")){
            List<User> usersIncludeAdmin = userRepository.findAll();
            List<User> userWithoutAdmin = usersIncludeAdmin.stream().filter(user->{
                String userName = user.getUsername();
                if(userName.equals("admin")){
                    return false;
                }
                return true;
            }).collect(Collectors.toList());

            return userWithoutAdmin;
        }
        return null;
    }

    // change role of user //

//    public String changeRoleOfUser(User user){
//        User fetchedUser = userRepository.findByUsername(user.getUsername());
//        Role userRoleOld = fetchedUser.getRole();
//        if(user.getRole().getRoleName() == userRoleOld.getRoleName()){
//            return "Sorry Both roles are same.";
//        }
//        user.setRole(user.getRole());
//        userRepository.save(user);
//        return "user role updated successfully";
//    }

    public User updateUser(int uid,User user) throws UserNotFoundException{
        try{
            User userFound =  (User) userRepository.findById(uid).get();
            userFound.setEnabled(user.isEnabled());
            return userRepository.save(userFound);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new UserNotFoundException("User Not Found with this ID");
        }
    }

    public String deleteUser(int id) throws UserNotFoundException{
        try{
            userRepository.deleteById(id);

            return "Employee Deleted Succesfully";
        }catch (Exception ex){
            ex.printStackTrace();
            throw new UserNotFoundException("Employee not found at the moment");
        }
    }
}
