package com.blogapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne()
    private Comment parentComment;
    @OneToMany(
            mappedBy = "parentComment",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Comment> replies = new ArrayList<>();
    public Comment(){

    }

}
