package com.trainticket.TrainBooking.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dabd")
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return "myDashboard"; 
    }
}
