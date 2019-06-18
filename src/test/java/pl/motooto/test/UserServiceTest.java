package pl.motooto.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.motooto.webapp.dao.UserDao;
import pl.motooto.webapp.model.User;
import pl.motooto.webapp.model.dto.UserDto;
import pl.motooto.webapp.service.UserService;
import pl.motooto.webapp.service.exception.EmailTakenException;
import pl.motooto.webapp.service.exception.PasswordsDontMatchException;
import pl.motooto.webapp.service.exception.UserNotFoundException;
import pl.motooto.webapp.service.exception.UsernameTakenException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class UserServiceTest {
    private static final String USERNAME_TAKEN = "testNick";
    private static final String EMAIL_TAKEN = "email@email.com";
    private static final String USERNAME_NOT_TAKEN = "johnsmith";
    private static final String EMAIL_NOT_TAKEN = "johnsmith@mail.com";

    private static UserService service;
    private static UserDto userWithTakenUsername;
    private static UserDto userWithTakenEmail;
    private static UserDto userWithNotMatchingPasswords;
    private static UserDto userThatShouldRegister;

    @BeforeClass
    public static void setup() {
        UserDao userDao = mock(UserDao.class);

        setupUsers();

        when(userDao.findByUsername(USERNAME_TAKEN)).thenReturn(new User());
        when(userDao.findByEmail(EMAIL_TAKEN)).thenReturn(new User());
        when(userDao.findByEmail(EMAIL_NOT_TAKEN)).thenReturn(null);
        when(userDao.findByUsername(USERNAME_NOT_TAKEN)).thenReturn(null);

        service = new UserService(userDao);
    }

    @Test(expected = UsernameTakenException.class)
    public void testRegisterNewUserUsernameTaken() throws EmailTakenException, UsernameTakenException, PasswordsDontMatchException {
        service.registerNewUser(userWithTakenUsername);
    }

    @Test(expected = EmailTakenException.class)
    public void testRegisterNewUserEmailTaken() throws EmailTakenException, UsernameTakenException, PasswordsDontMatchException {
        service.registerNewUser(userWithTakenEmail);
    }

    @Test(expected = PasswordsDontMatchException.class)
    public void testRegisterNewUserPasswordsDontMatch() throws PasswordsDontMatchException, UsernameTakenException, EmailTakenException {
        service.registerNewUser(userWithNotMatchingPasswords);
    }

    @Test
    public void testRegisterNewUserShouldPass() throws EmailTakenException, UsernameTakenException, PasswordsDontMatchException {
        service.registerNewUser(userThatShouldRegister);
    }

    @Test
    public void testGetUserExistingUsername() throws UserNotFoundException {
        service.getUser(USERNAME_TAKEN);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUserInvalidUsername() throws UserNotFoundException {
        service.getUser(USERNAME_NOT_TAKEN);
    }

    private static void setupUsers() {
        userWithTakenEmail = new UserDto();
        userWithTakenEmail.setUsername("someTestUsername");
        userWithTakenEmail.setEmail(EMAIL_TAKEN);
        userWithTakenEmail.setFirstName("John");
        userWithTakenEmail.setLastName("Smith");
        userWithTakenEmail.setPassword("password");
        userWithTakenEmail.setPasswordRepeat("password");

        userWithTakenUsername = new UserDto();
        userWithTakenUsername.setUsername(USERNAME_TAKEN);
        userWithTakenUsername.setEmail("another_test_mail@mail.com");
        userWithTakenUsername.setFirstName("John");
        userWithTakenUsername.setLastName("Smith");
        userWithTakenUsername.setPassword("password");
        userWithTakenUsername.setPasswordRepeat("password");

        userWithNotMatchingPasswords = new UserDto();
        userWithNotMatchingPasswords.setUsername(USERNAME_NOT_TAKEN);
        userWithNotMatchingPasswords.setEmail(EMAIL_NOT_TAKEN);
        userWithNotMatchingPasswords.setFirstName("John");
        userWithNotMatchingPasswords.setLastName("Smith");
        userWithNotMatchingPasswords.setPassword("password");
        userWithNotMatchingPasswords.setPasswordRepeat("password1");

        userThatShouldRegister = new UserDto();
        userThatShouldRegister.setUsername(USERNAME_NOT_TAKEN);
        userThatShouldRegister.setEmail(EMAIL_NOT_TAKEN);
        userThatShouldRegister.setFirstName("John");
        userThatShouldRegister.setLastName("Smith");
        userThatShouldRegister.setPassword("password");
        userThatShouldRegister.setPasswordRepeat("password");
    }
}
