package com.blogapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity(
        name = "Comment"
)
@Table(
        name = "comments"
)
@Getter
@Setter
public class Comment {
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
            name = "comment"
    )
    @NotEmpty(
            message = "Comment cannot be empty."
    )
    private String comment;
    @ManyToOne()
    @JoinColumn(
            name = "post_id"
    )
    private Post post;
    @ManyToOne()
    @JoinColumn(
            name = "user_id"
    )
    private User user;
    public Comment(){

    }

}
