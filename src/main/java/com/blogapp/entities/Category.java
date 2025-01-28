package com.blogapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity(
        name = "Category"
)
@Table(
        name = "categories"
)
@Getter
@Setter
public class Category {
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
            name = "category"
    )
    @NotEmpty(
            message = "Category cannot be empty"
    )
    private String category;
    @ManyToMany()
    @JoinTable(
            name = "category_post",
            joinColumns = {
                    @JoinColumn(
                            name = "category_id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "post_id"
                    )
            }
    )
    private List<Post> posts = new ArrayList<>();

    public Category(){

    }

}
