package com.drcp.config;


import com.drcp.entity.Role;
import com.drcp.repository.RoleRepository;


import lombok.RequiredArgsConstructor;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {


    private final RoleRepository roleRepository;



    @Override
    public void run(String... args) {


        createRoleIfNotExists("ROLE_ADMIN");

        createRoleIfNotExists("ROLE_CITIZEN");

        createRoleIfNotExists("ROLE_VOLUNTEER");

        createRoleIfNotExists("ROLE_DONOR");


    }





    private void createRoleIfNotExists(String roleName){


        if(roleRepository.findByName(roleName).isEmpty()){


            Role role = Role.builder()

                    .name(roleName)

                    .build();



            roleRepository.save(role);


        }


    }


}