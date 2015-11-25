package at.fhv.itb13.sportify.server.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(User.class)
public class UserTest {

    @Test
    public void testLogin() throws Exception {
        // arrange
        Properties properties = mock(Properties.class);
        whenNew(Properties.class).withNoArguments().thenReturn(properties);

        InitialContext initialContext = mock(InitialContext.class);
        whenNew(InitialContext.class).withArguments(properties).thenReturn(initialContext);

        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        // act
        boolean success = user.login();

        // assert
        assertTrue(success);
        verify(properties, times(1)).put(Context.SECURITY_AUTHENTICATION, "simple");
        verify(properties, times(1)).put(Context.SECURITY_PRINCIPAL, "uid=username,ou=fhv,ou=people,dc=uclv,dc=net");
        verify(properties, times(1)).put(Context.SECURITY_CREDENTIALS, "password");
        verify(initialContext, times(1)).close();
    }
}