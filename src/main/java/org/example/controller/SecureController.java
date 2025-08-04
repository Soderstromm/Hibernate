package org.example.controller;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure")
public class SecureController {


    @Secured("ROLE_READ")
    @GetMapping("/read")
    public String readData(Authentication authentication) {
        return "Hello, " + authentication.getName() + "! You have READ access.";
    }


    @RolesAllowed("ROLE_WRITE")
    @PostMapping("/write")
    public String writeData() {
        return "Data written!";
    }

    @PreAuthorize("hasRole('ROLE_WRITE') or hasRole('ROLE_DELETE')")
    @DeleteMapping("/delete")
    public String deleteData() {
        return "Data deleted!";
    }

    @PreAuthorize("#username == authentication.name")
    @GetMapping("/profile")
    public String getUserProfile(@RequestParam String username) {
        return "Profile of user: " + username;
    }
}

