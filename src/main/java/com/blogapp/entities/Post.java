package com.blogapp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(
        name = "Post"
)
@Table(
        name = "posts"
)
@Getter
@Setter
public class Post {
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @GeneratedValue(
            generator = "uuid"
    )
    @Id
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private String id;
    @Column(
            name = "post_title"
    )
    @NotEmpty(
            message = "Post title cannot be empty."
    )
    private String title;
    @Column(
            name = "post_content"
    )
    @NotEmpty(
            message = "Post content cannot be empty."
    )
    private String content;
    @Column(
            name = "post_image_url"
    )
    @NotEmpty(
            message = "Post image cannot be empty."
    )
    private String imageUrl;
    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    List<Comment> comments = new ArrayList<>();
    @ManyToMany(
            mappedBy = "posts",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    List<Category> categories = new ArrayList<>();
    @ManyToOne()
    @JoinColumn(
            name = "user_id"
    )
    private User user;
    @Column(
            name = "added_date"
    )
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;
    public Post(){

    }

}
