package com.drcp.controller.donor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DonorDashboardController {

    @GetMapping("/donor/dashboard")
    public String dashboard(){

        return "donor/dashboard";

    }

}
