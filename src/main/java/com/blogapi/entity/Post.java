package com.blogapi.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data // instead of getter and setter.@Lombo
@AllArgsConstructor // that is equivalent build a parametrize constructor.
@NoArgsConstructor// no argument constructor, default constructor does not come to the picture.
@Entity
@Table(name="posts", uniqueConstraints = {@UniqueConstraint(columnNames= {"title"})}) //instead of unique=true.
public class Post {  // this is one

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="description", nullable=false)
    private String description;

    @Column(name="content", nullable=false)
    private String content;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post")  //  and this is many
    private List<Comment> comments = new ArrayList<>();


}

//// post(id,title, description){  //equivalent build a constructor.
////
