package com.blogapi.payload;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}


//http://myblog-application-env.eba-bmnt2ht5.us-east-1.elasticbeanstalk.com/

//    http://myblog-application-env.eba-bmnt2ht5.us-east-1.elasticbeanstalk.com/api/auth/signup

//{
//        "name":"mike",
//        "username":"mike",
//        "email":"mike@gmail.com",
//        "password":"testing"
//        }


//error

//{
//        "timestamp": "2023-07-29T22:04:56.814+00:00",
//        "status": 404,
//        "error": "Not Found",
//        "path": "/api/auth/signup"
//        }