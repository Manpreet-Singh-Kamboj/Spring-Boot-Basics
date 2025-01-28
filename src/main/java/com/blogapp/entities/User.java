package com.blogapp.entities;

import com.blogapp.constants.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity(
        name = "User"
)
@Table(
        name = "users"
)
@Getter
@Setter
public class User {
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
            name = "user_full_name"
    )
    @NotEmpty(
            message = "Name is required."
    )
    @Size(
            min = 1,
            max = 50,
            message = "Name length must be greater than 1 character and less than 50 characters.")
    private String fullName;
    @Column(
            name = "user_email",
            unique = true
    )
    @NotEmpty(
            message = "Email is required."
    )
    @Email(
            message = "Email is not a valid email."
    )
    private String email;
    @Column(
            name = "user_password"
    )
    @NotEmpty(
            message = "Password cannot be empty."
    )
    @Size(
            min = 6,
            max = 32,
            message = "Password must be at least 6 characters."
    )
    private String password;
    @Column(
            name = "user_bio"
    )
    @Size(
            max = 1500
    )
    private String bio;
    @Column(
            name = "user_role"
    )
    @Enumerated(
            EnumType.STRING
    )
    private Roles role = Roles.USER;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Post> posts = new ArrayList<>();
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();
    public User(){

    }

}
