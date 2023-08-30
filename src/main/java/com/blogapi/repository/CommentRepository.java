package com.blogapi.repository;

import com.blogapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> { // if we want create custimise query then we can create in respository.

    //why we use this line only
  List<Comment> findByPostId(long id); // find the record by  id // findByPostId this is internally start write sql query


//    List<Comment> findByEmail(String email); // find the record by email.
//
//    List<Comment> findByName(String name); // fi
//
//    // this is the  way to build custom query  in hibernate.


  // List<Comment> findByIdAndEmail(long id, String email); //for And operator



    //  List<Comment> findByIdOrEmail(long id, String email); //for Or operator
 }
