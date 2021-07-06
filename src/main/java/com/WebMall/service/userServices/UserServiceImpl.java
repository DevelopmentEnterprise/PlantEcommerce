package com.WebMall.service.userServices;

import com.WebMall.model.Role;
import com.WebMall.model.User;
import com.WebMall.repository.RoleRepository;
import com.WebMall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void createNewUser(User user) {
        List<Role> roles = new ArrayList<>();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        roles.add(roleRepository.findByName("USER"));
        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public boolean save(User user) {
        if (user == null) return false;

        userRepository.save(user);
        return true;
    }

    @Override
    public User findByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean checkAuthUser() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser"){
            return false;
        }

        return true;
    }

    @Override
    public User getLoggedUser() {
        if (!checkAuthUser()) return null;

        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean checkForRole(String roleName) {
        if (!roleName.contains("ROLE_"))
            roleName = "ROLE_" + roleName;

        User loggedUser = getLoggedUser();
        List<Role> loggedUserRoles = loggedUser.getRoles();

        for (Role role : loggedUserRoles){
            if (role.getName().equals(roleName)){
                return true;
            }
        }

        return false;
    }
}
