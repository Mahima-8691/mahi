//package com.blogapi.controller;
//
//
//import com.blogapi.payload.LoginDto;
//import com.blogapi.repository.UserRepository;
//import com.blogapi.repository.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder; // it is automatically object generate here.(from object create Security config) spring IOC will take object from this method, and it will inject ere.
//
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//
//    @Autowired
//    private AuthenticationManager authenticationManager; // this is comming from  SecurityConfig.//auth controller require this object.
//
//    //http://localhost:8080/api/auth/signin
//    @PostMapping("/signin")
//    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())//it will take the username and email when you create obj then contsructor will call, In constructor we supply email from loging Dto, you extract the email or username and password.
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
//    }
//}
//
//
////{
////        "email":"nikita@gmail.com",
////        "name":"nkita",
//
////        "username":"nikitasss",
////        "password":"testing9"
////        }
