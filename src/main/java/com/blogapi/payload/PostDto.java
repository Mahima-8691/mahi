package com.blogapi.payload;



import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data// it will give setter and getter... encapsulation achieve with  only one annotation
public class PostDto {

    private long id;

   @NotEmpty // this comes for validation library
   @Size(min=2, message="Title Should be atleast 2 character")
   private String title;


    @NotEmpty(message="should not be empty") // if you add a custom message than it will not be default message it will take this message and display that in postman.
   // @NotEmpty
    @Size(min=4)
   private String description;

    @NotEmpty(message = "content may not be empty")
    private String content;

}


//{
//    "title":"java1",
//        "description":"learnig1",
//        "content":"1 feature"
//}