package com.blogapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
            name = "id"
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
            mappedBy = "post"
    )
    List<Comment> comments;
    @ManyToMany(
            mappedBy = "posts",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<Category> categories;
    @ManyToOne()
    @JoinColumn(
            name = "user_id"
    )
    private User user;
    @Temporal(
            TemporalType.DATE
    )
    @Column(
            name = "added_date"
    )
    private Date addedDate;
    public Post(){

    }

}
