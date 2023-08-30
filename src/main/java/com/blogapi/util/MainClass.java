package com.blogapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MainClass {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();// class upcasting.// new BCryptPasswordEncoder() this is come from security library  this is belongs to that.//BCryptPasswordEncoder this class , and we crete class object here.
        System.out.println(passwordEncoder.encode("test"));// there as encode() build in methiod present  insde the object is call encode .// after run password is encoded now.

    }
}
