package com.trainticket.TrainBooking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trainticket.TrainBooking.Pojo.User;
import com.trainticket.TrainBooking.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signUp(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("password") String password,
            @RequestParam("confirm_password") String confirmPassword,
            Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "signup";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);

        // Generate a confirmation token and set it in the user object
        user.generateConfirmationToken();
        
        userService.saveUser(user);
        
     // Send a confirmation email
      //  userService.sendConfirmationEmail(user);

        return "redirect:/user/signupSuccess"; 
    }
    
    @GetMapping("/signupSuccess")
    public String getSuccessSignUp()
    {
    	return "signupSuccess";
    }
    
    @GetMapping("/confirm")
    public String confirmEmail(@RequestParam(value = "token", required = false) String token, Model model) {
        // Validate token and activate user
        boolean isConfirmed = userService.confirmUser(token);

        if (isConfirmed) {
            return "redirect:http://localhost:8082/"; // Redirect to login page after successful confirmation
        } else {
            model.addAttribute("error", "Invalid or expired confirmation token");
            return "error"; // Show error page if the token is invalid
        }
    }
    
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {

        if (userService.authenticate(username, password)) {
        	session.setAttribute("username", username);
            return "redirect:/dabd/dashboard"; 
        }

        model.addAttribute("error", "Invalid username or password");
        return "login"; 
    }
}

