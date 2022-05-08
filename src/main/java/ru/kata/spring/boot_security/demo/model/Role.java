package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String roleName;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE},
            mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    public Role() {

    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> lists) {
        this.users = lists;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }

    @Override
    public String toString() {
        return "id-> " + id + " name: " + roleName;
    }
}
