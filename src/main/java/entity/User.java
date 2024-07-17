package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class User {
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private String login;
    @Setter
    @Getter
    private String password;

    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}
