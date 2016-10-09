package com.rsh.project.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fun-redoc on 06.10.16.
 */
@Entity
@Table(name="users")
@SuppressWarnings("unused")
//@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    @JsonIgnore
    // TODO password should ne crypted bevore store
    private String password;

    @NotEmpty
    @NotNull
    @Email
    private String email;

    @NotEmpty
    @NotNull
    private String fullName;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable( name = "users_roles",
                joinColumns = {@JoinColumn(name="user_id")},
                inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String name, String password, String email, String fullName, Set<Role> roles) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.roles = roles;
    }

    public User(Long id, String name, String password, String email, String fullName, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
