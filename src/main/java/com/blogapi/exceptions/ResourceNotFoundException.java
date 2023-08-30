package com.blogapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {  // status code 404  // here we are doing customise exception.






    // getter not require because it is simple class.


    public ResourceNotFoundException(long id){ // we are creating constructor. //in postServiceimpl we creating object by using lembda expression.
         super("Resource not found id:"+id); // super key word automatically print the msg on response.

    }
}


// I will create object of the class, a record for that id not found ,when I am not able to find record for that id, I will give this msg.
//i want to throw exception on postman response , when the record is not found 1000  , it should create an object of ResourceNotFound throw an exception ResourceNotFound with id 1000 message should be changed.
