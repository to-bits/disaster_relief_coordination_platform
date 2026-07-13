package com.drcp.controller.volunteer;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class VolunteerDashboardController {



    @GetMapping("/volunteer/dashboard")
    public String dashboard(){

        return "volunteer/dashboard";

    }

}