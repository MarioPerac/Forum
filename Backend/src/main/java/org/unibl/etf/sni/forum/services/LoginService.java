package org.unibl.etf.sni.forum.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.sni.forum.models.dto.User;
import org.unibl.etf.sni.forum.models.entites.UserEntity;
import org.unibl.etf.sni.forum.repositories.UserRepository;

@Service
public class LoginService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public LoginService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public User login(String username, String password){
        UserEntity userEntity = userRepository.findByUsername(username);

        if(userEntity == null)
            return null;

        if(userEntity.getPassword().equals(password)){
            return modelMapper.map(userEntity, User.class);
        }

        return null;
    }
}
