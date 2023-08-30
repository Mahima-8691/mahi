
package com.blogapi.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@Configuration// whenever you create config class  to tell  springboot it is configuration class.
@EnableWebSecurity// i don't want to enable default security configuration enable this websecurity configuration part. // if you dont want to enalble security of the desfault password and you want youer username or password.
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  @Autowired
    private CustomUserDetailsService userDetailsService;
    @Bean
    PasswordEncoder passwordEncoder(){ //ask suman ?
        return new BCryptPasswordEncoder();
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()// which request has accepted or denies.
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()//permitAll() this url is open , means publically.
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();//dialog pop box
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {// we are not object is created this is bild in
        auth.userDetailsService(userDetailsService) // this is bildIn method.
                .passwordEncoder(passwordEncoder());// again this is bildIn method.
    }

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

}


