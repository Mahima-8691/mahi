package com.blogapi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment { //and this is many // first one is for the table and second one is for the variable.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "body", nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY) //it can access only that database which are in use
    @JoinColumn(name = "post_id", nullable = false) // because of join column we add foreign id, that's y column name is given post_id
    private Post post; //this is one       // join the post table with  the comment table  by creating "post_id" column in our comments .then foreign key get created "post_id".
  // here is one more variable.
}
