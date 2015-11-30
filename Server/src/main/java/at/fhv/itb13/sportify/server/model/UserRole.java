package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "userrole")
public class UserRole extends PersistentObjectImpl {

    private String _name;
    private String _description;
    private Set<User> _users = new HashSet<>();
    private Set<UserRight> _userRights = new HashSet<>();

    public UserRole() {
    }

    public UserRole(String name, String description, Set<User> users, Set<UserRight> userRights) {
        _name = name;
        _description = description;
        _users = users;
        _userRights = userRights;
    }

    @Column(name = "name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_userrole", joinColumns = {@JoinColumn(name = "userrole_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "username", nullable = false, updatable = false)})
    public Set<User> getUsers() {
        return _users;
    }

    public void setUsers(Set<User> users) {
        _users = users;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userrole_userright", joinColumns = {@JoinColumn(name = "userrole_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "userright_id", nullable = false, updatable = false)})
    public Set<UserRight> getUserRights() {
        return _userRights;
    }

    public void setUserRights(Set<UserRight> userRights) {
        _userRights = userRights;
    }
}
