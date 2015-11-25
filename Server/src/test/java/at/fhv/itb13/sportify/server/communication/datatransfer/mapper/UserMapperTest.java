package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.UserMother;
import at.fhv.itb13.sportify.server.model.User;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTOImpl;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserMapper.class)
public class UserMapperTest {

    private UserMapper _userMapper;

    @Before
    public void setUp() {
        _userMapper = new UserMapper();

    }

    @Test
    public void toDomainObjectReturnUser() {

        // arrange
        UserDTO userDTO = new UserDTOImpl();
        userDTO.setId(IdGenerator.createId());
        userDTO.setName("name");
        userDTO.setPassword("password");

        //act
        User user = _userMapper.toDomainObject(userDTO);

        //assert
        assertEquals(userDTO.getName(), user.getUsername());
        assertEquals(userDTO.getPassword(), user.getPassword());

    }

    @Test
    public void toDomainObjectReturnNull() {

        // arrange
        UserDTO userDTO = null;

        //act
        User user = _userMapper.toDomainObject(userDTO);

        //assert
        assertEquals(user, null);

    }

    @Test
    public void toDTOObjectReturnUserDTO() {

        // arrange
        UserMother userMother = new UserMother();
        User user = userMother.instance();

        //act
        UserDTO userDTO = _userMapper.toDTOObject(user);

        //assert
        assertEquals(user.getPassword(), userDTO.getPassword());
        assertEquals(user.getUsername(), userDTO.getName());

    }

    @Test
    public void toDTOObjectReturnNull() {

        // arrange
        User user = null;

        //act
        UserDTO userDTO = _userMapper.toDTOObject(user);

        //assert
        assertEquals(userDTO, null);

    }
}
