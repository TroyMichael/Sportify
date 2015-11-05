package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by Caroline on 05.11.2015.
 */
public class User extends PersistentObjectImpl {

    private String _username;
    private String _password;


    public User(){

    }

    public User(String username, String password){
        _username = username;
        _password = password;
    }

    public void setUsername(String username){
        _username = username;
    }

    public void setPassword(String password){
        _password = password;
    }

    public String getUsername(){
        return _username;
    }

    public String getPassword(){
        return _password;
    }

    public boolean login(){

        Properties env = new Properties();
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
       // env.put(Context.SECURITY_PRINCIPAL,"dc="+ _username +",dc=uclv,dc=net");
        env.put(Context.SECURITY_PRINCIPAL,"uid="+ _username +",ou=People,dc=uclv,dc=net");
        env.put(Context.SECURITY_CREDENTIALS, _password);

        try {
            Context context = new InitialContext(env);
            context.close();
            return true;
        } catch (NamingException e) {
            e.printStackTrace();
            return false;
        }

    }

}
