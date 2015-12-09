package at.fhv.itb13.sportify.client.communication;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by KYUSS on 02.11.2015.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(ServiceLocator.class)
public class ServiceLocatorTest extends TestCase {


    @Test
    public void getURL() {
        /*Method m = Whitebox.getMethod(ServiceLocator.class, "getUrl");
        m.invoke()
        ServiceLocator serviceLocator
        assertEquals(serviceLocator.getUrl(),);*/
    }

}