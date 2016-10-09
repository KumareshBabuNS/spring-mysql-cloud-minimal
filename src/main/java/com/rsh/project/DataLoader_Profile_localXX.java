package com.rsh.project;

import java.util.*;

import javax.annotation.PostConstruct;

import com.google.common.collect.Sets;
import com.rsh.project.security.domain.Role;
import com.rsh.project.security.domain.User;
import com.rsh.project.security.repository.RoleRepository;
import com.rsh.project.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"localH2", "localMySql"})
@SuppressWarnings("unused")
public class DataLoader_Profile_localXX {


    private UserRepository userRepository;
    private RoleRepository roleRepository;

	@Autowired
	public DataLoader_Profile_localXX(UserRepository userRepository,
                                      RoleRepository roleRepository ){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
	}

	// TODO only in local profiles
	@PostConstruct
	private void loadData(){

        userRepository.deleteAll();


        // create users
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleProject = new Role("ROLE_PROJECT");
        Role roleUser = new Role("ROLE_USER");

        Set<Role> adminRoleSet = new HashSet<>();
        adminRoleSet.add(roleAdmin);

        Set<Role> userRoleSet = new HashSet<>();
        userRoleSet.add(roleProject);
        userRoleSet.add(roleUser);

        User userAdmin = new User(
                "admin",
                "password",
                "admin@example.com",
                "Mister Administrator",
                adminRoleSet
                );
        User userProject = new User(
                "project",
                "password",
                "project@example.com",
                "Mister Project User",
                Sets.newHashSet(roleProject,roleUser)
                );

        User savedAdminUser = userRepository.save(userAdmin);
        User savedProjectUser = userRepository.save(userProject);
	}

}