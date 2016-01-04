package at.fhv.itb13.sportify.server.model;

import org.hibernate.annotations.Parent;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.Properties;

@Embeddable
public class User {

    private Person _person;
    private String _username;
    private String _password;

    public User() {
    }

    public User(String username, String password) {
        _username = username;
        _password = password;
    }

    public User(Person person, String username, String password) {
        _person = person;
        _username = username;
        _password = password;
    }

    @Parent
    @OneToOne
    public Person getPerson() {
        return _person;
    }

    public void setPerson(Person person) {
        _person = person;
    }

    @Column(name = "username")
    public String getUsername() {
        return _username;
    }

    public void setUsername(String username) {
        _username = username;
    }

    @Transient
    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    /**
     * lets the user login if the correct login data has been entered
     */
    public boolean login() {
        Properties env = new Properties();
        env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldaps://ldap.fhv.at:636");

        DirContext context = null;
        try {
            context = new InitialDirContext(env);

            // configure search controls to search whole subtree
            SearchControls ctls = new SearchControls();
            ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            // define filter to search for user with the specified username
            String filter = "(uid=" + _username + ")";

            NamingEnumeration namingEnum = context.search("dc=uclv,dc=net", filter, ctls);
            if ((namingEnum != null) && namingEnum.hasMore()) {
                SearchResult searchResult = (SearchResult) namingEnum.next();

                // check if search returned unique result
                if ((searchResult != null) && !namingEnum.hasMore()) {
                    // authenticate user with specified password
                    env.put(Context.SECURITY_AUTHENTICATION, "simple");
                    env.put(Context.SECURITY_PRINCIPAL, searchResult.getNameInNamespace());
                    env.put(Context.SECURITY_CREDENTIALS, _password);

                    context = new InitialDirContext(env);
                    return true;
                }
            }
            return false;
        } catch (NamingException e) {
            return false;
        } finally {
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean equals(Object object) {
        // if it's the same reference, return true
        if (this == object) {
            return true;
        }

        // if it's null or not a user object, return false
        if (object == null || !(object instanceof User)) {
            return false;
        }

        User other = (User) object;
        return (_username != null) && (_username.equals(other.getUsername()));
    }

    @Override
    public int hashCode() {
        if (_username != null) {
            return _username.hashCode();
        } else {
            return super.hashCode();
        }
    }
}
