package com.blogapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity(
        name = "Like"
)
@Table(
        name = "likes"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Like {
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
    @JoinColumn(
            name = "user_id"
    )
    @ManyToOne()
    private User user;
    @JoinColumn(
            name = "post_id"
    )
    @ManyToOne()
    private Post post;
    @JoinColumn(
            name = "comment_id"
    )
    @ManyToOne()
    private Comment comment;
}
