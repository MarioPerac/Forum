package org.unibl.etf.sni.forum.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.sni.forum.base.CrudController;
import org.unibl.etf.sni.forum.models.dto.User;
import org.unibl.etf.sni.forum.models.requests.UserRequest;
import org.unibl.etf.sni.forum.services.UserService;

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
    public String login(@RequestBody UserRequest userRequest){
       return userService.login(userRequest);
   }

}
