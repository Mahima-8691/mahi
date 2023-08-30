//package com.blogapi.config;
//
//
//package com.blogapi.config;
//
//import antlr.BaseAST;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//
//
//@Configuration// whenever you create config class  to tell  springboot it is configuration class.
//@EnableWebSecurity// i don't want to enable default security configuration enable this websecurity configuration part.
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override //beause inherit from WebSecurityConfigurerAdapter to SecurityConfig.
//    protected void configure(HttpSecurity http) throws Exception {
//
//        //http is reference variable  which automatically to get its initialize with object address,  On its own on that object address you taken call   .csrf().disable()
//        http
//                .csrf().disable()//
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/**").permitAll() // this is open for only Get.// come second time.
//                .antMatchers("/api/auth/**").permitAll() // come third time.// bedefautl is signing up.this particular person is admin only. and this part will be remove.// and this is open for only Get.auth.// for sign up url , we keep sign up url open.
//                //  .antMatchers("/api/auth/signup").hasRole("Admin")//come fourth time. // when signup as admin then this url will work.otherwise this url is not work, admin can create users.
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//    }

// we are not going with InMemoryAuthentication.
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() { // insited of database , i am using  InMemoeryAuthontication creating two object storing usename and password.
//        UserDetails user =// object create
//                User.builder().username("pankaj").password(passwordEncoder() //using user we call buider method build the objet using builder method.
//                        .encode("password")).roles("USER").build();// we rights of user.login with user credential, user can access certain featureur controlling ike that .
//        UserDetails admin =  // object create
//                User.builder().username("admin").password(passwordEncoder() // lwhen u give username admin and password is admin you got admin rights.
//                        .encode("admin")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
    // two object ome is user second one is dmin.
    // here two roles user and admin // this should be store in database but i am testing my code in MemoeryAuthontication slowly we doing progress.we don't have confusion.
    // first we store username and password properties file  from properties file  i am storing username and password  into InMemoeryAuthontication object we created .
// now username and password picked from here.

//}



