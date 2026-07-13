package com.drcp.controller.citizen;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class CitizenDashboardController {



    @GetMapping("/citizen/dashboard")
    public String dashboard(){

        return "citizen/dashboard";

    }

}