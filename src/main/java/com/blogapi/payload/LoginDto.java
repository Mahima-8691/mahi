package com.blogapi.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}

//http://myblog-application-env.eba-bmnt2ht5.us-east-1.elasticbeanstalk.com/


//http://myblog-application-env.eba-bmnt2ht5.us-east-1.elasticbeanstalk.com/api/auth/signin
//{
//        "usernameOrEmail":"mike@gmail.com",
//        "password":"testing"
// }

//error

//{
//        "timestamp": "2023-07-29T22:09:31.371+00:00",
//        "message": "Bad credentials",
//        "details": "uri=/api/auth/signin"
//        }