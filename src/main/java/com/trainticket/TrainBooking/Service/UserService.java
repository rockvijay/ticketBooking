package com.trainticket.TrainBooking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainticket.TrainBooking.Pojo.User;
import com.trainticket.TrainBooking.Respository.UserRepository;


import java.util.UUID;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;

    /**
     * Save user and send confirmation email.
     */
    public void saveUser(User user) {
        // Generate a unique confirmation token
        String token = UUID.randomUUID().toString();
        user.setConfirmationToken(token);  // Assume you add a field in the User entity for this

        // Save the user (but user is not yet activated)
        userRepository.save(user);
        
        // Send confirmation email
        String confirmationUrl = "http://localhost:8082/user/confirm?token=" + token;
        String subject = "Confirm your email";
        String message = "Please confirm your registration by clicking the link: " + confirmationUrl;

        emailService.sendSimpleMessage(user.getEmail(), subject, message);
    }

    /**
     * Authenticate user by username and password.
     */
    public boolean authenticate(String username, String password) {
        // Fetch user by username
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password) && user.isEnabled()) {
            // User must be activated (confirmed) to authenticate successfully
            return true;
        }
        return false; // Authentication failed
    }

    /**
     * Confirm user by token and activate the account.
     */
    public boolean confirmUser(String token) {
        // Find user by confirmation token
        Optional<User> userOptional = userRepository.findByConfirmationToken(token);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // Activate user by setting enabled to true
            user.setEnabled(true);
            user.setConfirmationToken(null); // Remove the token after confirmation
            userRepository.save(user);
            return true;
        }
        
        return false; // Token not found or expired
    }
    
}

