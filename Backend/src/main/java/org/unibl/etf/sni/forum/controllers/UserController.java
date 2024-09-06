package org.unibl.etf.sni.forum.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.sni.forum.base.CrudController;
import org.unibl.etf.sni.forum.models.dto.User;
import org.unibl.etf.sni.forum.models.requests.ActivationRequest;
import org.unibl.etf.sni.forum.models.requests.UserRequest;
import org.unibl.etf.sni.forum.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

   private UserService userService;

   public UserController(UserService userService){
       this.userService = userService;
   }

   @PostMapping("/register")
    public User register(@RequestBody UserRequest userRequest){
        return userService.register(userRequest);
   }

   @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest, HttpServletResponse response){
       String token = userService.login(userRequest);

       if (token != null) {
           Map<String, String> data = new HashMap<>();
           data.put("token", token);
           return ResponseEntity.ok(data);
       }
       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   }

   @GetMapping("/role")
    public Map<String, String> getRole(){
       String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = userService.getRole(username);
       Map<String, String> response = new HashMap<>();
       response.put("role", role);
       return response;
   }

   @PreAuthorize("hasRole('ADMIN')")
   @GetMapping("/unactivated")
    public List<User> getAllUnactivated(){
       return userService.getUnactivatedUsers();
   }

   @PreAuthorize("hasRole('ADMIN')")
   @PutMapping("/{username}/activation")
   @ResponseStatus(HttpStatus.CREATED)
    public void setActivation(@PathVariable String username, @RequestBody ActivationRequest request){
        userService.setActivated(username, request);
   }
}
