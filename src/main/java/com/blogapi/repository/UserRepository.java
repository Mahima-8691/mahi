//package com.springboot.blog.repository;
//
//import com.springboot.blog.entity.User;
//import org.springframework.data.domain.Example;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.Optional;
//public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByEmail(String email);
//    Optional<User> findByUsernameOrEmail(String username, String email);
//    Optional<User> findByUsername(String username);
//    Boolean existsByUsername(String username);
//    Boolean existsByEmail(String email);
//}

package com.blogapi.repository;

        import com.blogapi.entity.User;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);//this method check the email based on email id if the record is found based on email it will return optional class object with the data
    Optional<User> findByUsernameOrEmail(String username,String email);
    Optional<User> findByUsername(String username); // select * from user where username =?
    boolean existsByUsername(String username);// to check weather username exist or not
    boolean  existsByEmail(String username);
}








