package com.blogapi.controller;


import com.blogapi.payload.PostDto;
import com.blogapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts") // path or endpoint.
public class PostController {

     private PostService postService;

    public PostController(PostService postService) { // constructor name same as  class name .// constructor dependency.

        this.postService = postService;
    }

        //http://localhost:8080/api/posts
    @PreAuthorize("hasRole('ADMIN')") // only you login Admin then you create Post. // when you login as user then you can not.
    @PostMapping // for save  // status code-201(whenever you're creating a record ) // ResponseEntity<?> if we are replace with generic then  method can write any kind of value.
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result) { //@Valid if we are not using this annotation then valid will not loaded //take the data with the help of post, data will go to controller PostDto and take this Dto and give it to service layer.
        //@RequestBody take the content from json(postman)  to post dto.
        //BindingResult ,Fist check ,here is any error during validation.

      if(result.hasErrors()){

          return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }



        PostDto saveDto = postService.createPost(postDto);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);

    }

    //http://localhost:8080/api/posts?id=1    // this is query parameter. it is not use in this project. To read variable value not a path variable. (we use for query parameter is (@RequestParam("id")) long id ).

    //http://localhost:8080/api/posts/1       // this is path parameter,(we use for path parameter is (@PathVariable("id") long id). This and
    @GetMapping("/{id}")  // status code-200(whenever you're fetching a record )this should be match , getting the record from database is @GetMapping.
     public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){ // 2and initialize here.//status code=200 (fetching the record from db and the record is found  you should return 200)
        PostDto dto=postService.getPostById(id);//
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

   // http://localhost:8080/api/posts?pageNo=1&pageSize=3&sortBy=title&sortDir=asc  // we can write id,title,description,content for sort.   //this url // 0 means 1st page , 1 means 2nd page.(2nd page ill get 3 record)
   // http://localhost:8080/api/posts
   // http://localhost:8080/api/posts?pageNo=1&pageSize=3&sortBy=title&sortDir=asc   // we can write for sortDir=asc,desc , if you dont give anything then asc order.
    @GetMapping // find the record
    public List<PostDto> getAllPosts( // call this method. and this controller call handler method instead of this line method. this handler method read this url value and the page size and put into this.
          @RequestParam(value="pageNo", defaultValue = "0", required = false) int pageNo,
          @RequestParam(value="pageSize", defaultValue = "5", required = false) int pageSize,
          @RequestParam(value="sortBy", defaultValue = "id", required = false) String sortBy,
          @RequestParam(value="sortDir", defaultValue = "asc", required = false) String sortDir

    ){
        List<PostDto> postdtos = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return postdtos;


    }
    //http://localhost:8080/api/posts/1
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){ // status code is = 200
              postService.deletePost(id); // only msg not save delete record.
        return new ResponseEntity<>("Post is deleted!!",HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/1
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id,@RequestBody PostDto postDto){ // url+ Json, status code is = 200
        PostDto dto = postService.updatePost(id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

//post is deleted!!",

}

//when we click send in postman , that data go to PostDto controller layer then calling the service layer which is service layer take the postDto and that dto  should save into the database.
// but it can not save the Dto to database then we create Dto object in Entity,  save after saving  whatever entity save gets a detail convert tht save Post to PostDto and
// return the dto back to controller.
//when we return ResponseEntity<PostDto>  so that the content  can be see in the response in postman once I save.
// we Repose in postman because of  (return new ResponseEntity<>(saveDto, HttpStatus.ACCEPTED.CREATED)) its returning Dto.
// HttpStatus.ACCEPTED.CREATED we write this then we get in postman status 201 Created.


//    "title":"java1",
//        "description":"learnig1",
//        "content":"1 feature"



//http://localhost:8080/api/posts
//{
//        "title":"JWT Token Classes123",
//        "description":"JWT Token explaination123",
//        "content":"JWT Tutorials123"
//        }