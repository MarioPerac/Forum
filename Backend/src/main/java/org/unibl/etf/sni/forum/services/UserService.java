package org.unibl.etf.sni.forum.services;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.unibl.etf.sni.forum.base.CrudJpaService;
import org.unibl.etf.sni.forum.models.dto.Mail;
import org.unibl.etf.sni.forum.models.dto.User;
import org.unibl.etf.sni.forum.models.entites.UserEntity;
import org.unibl.etf.sni.forum.models.requests.ActivationRequest;
import org.unibl.etf.sni.forum.models.requests.UserRequest;
import org.unibl.etf.sni.forum.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends CrudJpaService<UserEntity, String> implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private MailService mailService;

    public UserService(UserRepository userRepository,
                       ModelMapper modelMapper,
                       BCryptPasswordEncoder encoder,
                       @Lazy AuthenticationManager authenticationManager,
                       JwtService jwtService,
                       MailService mailService) {
        super(userRepository, UserEntity.class, modelMapper);

        this.userRepository =userRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.mailService = mailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User register(UserRequest userRequest){

        UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        userEntity = userRepository.saveAndFlush(userEntity);
        entityManager.refresh(userEntity);

        return modelMapper.map(userEntity, User.class);
    }

    public String login(UserRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(),userRequest.getPassword()));

        UserEntity userEntity = userRepository.findByUsername(userRequest.getUsername());
        if(userEntity == null || userEntity.getActivated() == null || !userEntity.getActivated()){
            return null;
        }
                if(authentication.isAuthenticated()){
                    return jwtService.generateToken(userRequest.getUsername());
                }
                return null;
    }

    public User findByUsername(String username){
        UserEntity userEntity = userRepository.findByUsername(username);

        User user = modelMapper.map(userEntity, User.class);
        user.setRole(userEntity.getRoleByRoleId().getName());
        return user;
    }

    public String getRole(String username){
        return userRepository.findByUsername(username).getRoleByRoleId().getName();
    }

    public List<User> getUnactivatedUsers(){
        List<UserEntity> entities = userRepository.getAllByActivated(null);

        return entities.stream().map(e -> {
            User user = modelMapper.map(e, User.class);
            user.setRole(e.getRoleByRoleId().getName());
            return user;
        }).collect(Collectors.toList());
    }

    public void setActivated(String username, ActivationRequest request){
         UserEntity userEntity = userRepository.findByUsername(username);
        userEntity.setActivated(request.getActivated());
        userEntity.setRoleId(request.getRoleId());
        userEntity = userRepository.saveAndFlush(userEntity);
        entityManager.refresh(userEntity);

        final UserEntity modifiedUser = userEntity;
        new Thread(() -> {
            String fullName = modifiedUser.getName() +  " " + modifiedUser.getSurname();
            String subject = "Forum - Activation response";
            String htmlContent ="<html>"
                    + "<body>"
                    + "<p>Dear " + fullName + ",</p>"
                    + "<p>Your account request is: " +( request.getActivated() ? "ACTIVATED, role= " + modifiedUser.getRoleByRoleId().getName() + ".": "DENIED.") +"</p>"
                    + "<p>Kind regards,</p>"
                    + "<p>Forum</p>"
                    + "</body>"
                    + "</html>";
            mailService.sendMail(new Mail(modifiedUser.getMail(), subject, fullName, modifiedUser.getUsername(), htmlContent));
        }).start();
    }
}
