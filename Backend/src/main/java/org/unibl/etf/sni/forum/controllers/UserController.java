package org.unibl.etf.sni.forum.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.sni.forum.base.CrudController;
import org.unibl.etf.sni.forum.models.dto.User;
import org.unibl.etf.sni.forum.models.requests.UserRequest;
import org.unibl.etf.sni.forum.services.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController extends CrudController<String, UserRequest, User> {

   private UserService userService;

   public UserController(UserService userService){
       super(userService, User.class);
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

}
