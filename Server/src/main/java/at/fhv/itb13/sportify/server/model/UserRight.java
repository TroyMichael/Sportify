package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "userright")
public class UserRight extends PersistentObjectImpl {

    private RightName _name;
    private String _description;
    private Set<UserRole> _userRoles = new HashSet<>();

    public UserRight() {
    }

    public UserRight(RightName name, String description, Set<UserRole> userRoles) {
        _name = name;
        _description = description;
        _userRoles = userRoles;
    }

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    public RightName getName() {
        return _name;
    }

    public void setName(RightName name) {
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
    @JoinTable(name = "userrole_userright", joinColumns = {@JoinColumn(name = "userright_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "userrole_id", nullable = false, updatable = false)})
    public Set<UserRole> getUserRoles() {
        return _userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        _userRoles = userRoles;
    }
}
