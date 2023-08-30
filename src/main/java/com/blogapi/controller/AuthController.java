package com.blogapi.controller;

import com.blogapi.entity.Role;
import com.blogapi.entity.User;
import com.blogapi.payload.JWTAuthResponse;
import com.blogapi.payload.LoginDto;
import com.blogapi.payload.SignUpDto;
import com.blogapi.repository.UserRepository;
import com.blogapi.repository.RoleRepository;
import com.blogapi.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder; // it is automatically object generate here.(from object create Security config) spring IOC will take object from this method, and it will inject ere.


    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private AuthenticationManager authenticationManager; // this is comming from  SecurityConfig.//auth controller require this object.

    //http://localhost:8080/api/auth/signin
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    //http://localhost:8080/api/auth/signup

    // localhost:8080/api/auth/signup  // via this url I submit to JSON.
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){ // return type is generic because , multiple kind of return happen I am keeping up generic.
        System.out.println(signUpDto);
    // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){ // take the  username and check username is  exist.(true or false)) // this is custom method existsByUsername
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST); // if  username is exit it is going to return  username is already taken HttpStatus.BAD_REQUEST.
        }
        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){ // it will take email id from this object signUpDto, and it will check , go database automatically check .
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST); // if  email is exit it is going to return  email is already taken HttpStatus.BAD_REQUEST.
        }
        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        userRepository.save(user);



       Role roles = roleRepository.findByName("ROLE_ADMIN").get(); // the user whom i am register  should automatically  be an admin user , if I am signup user default should become in admin.
        user.setRoles(Collections.singleton(roles));


        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}



//{
//        "email":"nikita@gmail.com",
//        "name":"nkita",

//        "username":"nikitasss",
//        "password":"testing9"
//        }


//http://localhost:8080/api/auth/signin
//{
//        "usernameOrEmail":"mike@gmail.com",
//        "password":"testing"
//
//}

