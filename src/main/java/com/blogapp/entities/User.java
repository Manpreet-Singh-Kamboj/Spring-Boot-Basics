package com.blogapp.entities;

import com.blogapp.constants.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(
        name = "User"
)
@Table(
        name = "users"
)
@Getter
@Setter
@AllArgsConstructor
@Builder
@Data
public class User implements UserDetails {
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
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Like> likes = new ArrayList<>();
    public User(){

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
