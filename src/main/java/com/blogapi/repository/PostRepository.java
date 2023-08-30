package com.blogapi.repository;

import com.blogapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
   // Post save(Post post);// we are using wrapper class of primary key of id.// primary key is a wrapper class.
}
